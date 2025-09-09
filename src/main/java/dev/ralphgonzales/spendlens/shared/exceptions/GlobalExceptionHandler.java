package dev.ralphgonzales.spendlens.shared.exceptions;

import dev.ralphgonzales.spendlens.shared.dto.ApiFieldError;
import dev.ralphgonzales.spendlens.shared.dto.ErrorResponse;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.i18n.MessageResolver;
import dev.ralphgonzales.spendlens.shared.persistence.constraints.translator.DbConstraintTranslator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageResolver messageResolver;
    private final DbConstraintTranslator dbConstraintTranslator;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolations(ConstraintViolationException ex, HttpServletRequest request) {
        CommonErrorCode error = CommonErrorCode.FIELD_VALIDATION_FAILED;

        List<ApiFieldError> errors = ex.getConstraintViolations().stream()
                        .map(violation-> new ApiFieldError(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()))
                        .toList();

        ErrorResponse body = new ErrorResponse(
                error.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                error.getCode(),
                messageResolver.getMessage(error.getMessageKey()),
                errors);

        return ResponseEntity.status(error.getStatus()).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Throwable rootCause = ex.getMostSpecificCause();
        CommonErrorCode dateError = CommonErrorCode.DATE_INVALID_FORMAT;
        CommonErrorCode jsonError = CommonErrorCode.JSON_INVALID_FORMAT;

        if(rootCause instanceof DateTimeParseException) {
            ErrorResponse body = new ErrorResponse(
                    dateError.getStatus().value(),
                    request.getRequestURI(),
                    Instant.now(),
                    dateError.getCode(),
                    messageResolver.getMessage(dateError.getMessageKey()),
                    null);

            return ResponseEntity.status(dateError.getStatus()).body(body);
        } else if(rootCause instanceof InvalidEnumException e){
            ErrorResponse body = new ErrorResponse(
                    e.getStatus().value(),
                    request.getRequestURI(),
                    Instant.now(),
                    e.getCode(),
                    messageResolver.getMessage(e.getMessage()),
                    null);

            return ResponseEntity.status(e.getStatus()).body(body);
        }

        ErrorResponse body = new ErrorResponse(
                jsonError.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                jsonError.getCode(),
                messageResolver.getMessage(jsonError.getMessageKey()),
                null);

        return ResponseEntity.status(jsonError.getStatus()).body(body);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessValidationError(BusinessValidationException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(
                ex.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                ex.getCode(),
                ex.getMessage(),
                null)
        );
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimistic(HttpServletRequest request) {
        CommonErrorCode error = CommonErrorCode.VERSION_CONFLICT;

        ErrorResponse body = new ErrorResponse(
                error.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                error.getCode(),
                messageResolver.getMessage(error.getMessageKey()),
                null);

        return ResponseEntity.status(error.getStatus()).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request){
        CommonErrorCode error =  dbConstraintTranslator.map(ex);

        ErrorResponse body = new ErrorResponse(
                error.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                error.getCode(),
                messageResolver.getMessage(error.getMessageKey())
                ,null);

        return ResponseEntity.status(error.getStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        CommonErrorCode error = CommonErrorCode.UNEXPECTED_ERROR;

        ErrorResponse body = new ErrorResponse(
                error.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                error.getCode(),
                messageResolver.getMessage(error.getMessageKey()),
                null);

        return ResponseEntity.status(error.getStatus()).body(body);
    }
}
