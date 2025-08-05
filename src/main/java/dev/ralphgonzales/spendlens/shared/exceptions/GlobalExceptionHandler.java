package dev.ralphgonzales.spendlens.shared.exceptions;

import dev.ralphgonzales.spendlens.shared.dto.ApiFieldError;
import dev.ralphgonzales.spendlens.shared.dto.ErrorResponse;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolations(ConstraintViolationException ex, HttpServletRequest request) {
        List<ApiFieldError> errors = ex.getConstraintViolations().stream()
                        .map(violation-> new ApiFieldError(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()))
                        .toList();

        return ResponseEntity.status(CommonErrorCode.FIELD_VALIDATION_FAILED.getStatus()).body(new ErrorResponse(
                CommonErrorCode.FIELD_VALIDATION_FAILED.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                CommonErrorCode.INVALID_DATE_FORMAT.getCode(),
                messageSource.getMessage(CommonErrorCode.FIELD_VALIDATION_FAILED.getMessageKey(),null,LocaleContextHolder.getLocale()),
                errors)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Throwable rootCause = ex.getMostSpecificCause();

        if(rootCause instanceof DateTimeParseException) {
            return ResponseEntity.status(CommonErrorCode.INVALID_DATE_FORMAT.getStatus()).body(new ErrorResponse(
                    CommonErrorCode.INVALID_DATE_FORMAT.getStatus().value(),
                    request.getRequestURI(),
                    Instant.now(),
                    CommonErrorCode.INVALID_DATE_FORMAT.getCode(),
                    messageSource.getMessage(CommonErrorCode.INVALID_DATE_FORMAT.getMessageKey(),null,LocaleContextHolder.getLocale()),
                    null)
            );
        }

        return ResponseEntity.status(CommonErrorCode.INVALID_JSON_FORMAT.getStatus()).body(new ErrorResponse(
                CommonErrorCode.INVALID_JSON_FORMAT.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                CommonErrorCode.INVALID_JSON_FORMAT.getCode(),
                messageSource.getMessage(CommonErrorCode.INVALID_JSON_FORMAT.getMessageKey(), null, LocaleContextHolder.getLocale()),
                null)
        );
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(CommonErrorCode.UNEXPECTED_ERROR.getStatus()).body(new ErrorResponse(
                CommonErrorCode.UNEXPECTED_ERROR.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                CommonErrorCode.UNEXPECTED_ERROR.getCode(),
                messageSource.getMessage(CommonErrorCode.UNEXPECTED_ERROR.getMessageKey(),null, LocaleContextHolder.getLocale()),
                null)
        );
    }
}
