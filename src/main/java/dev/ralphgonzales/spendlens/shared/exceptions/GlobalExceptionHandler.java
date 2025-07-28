package dev.ralphgonzales.spendlens.shared.exceptions;

import dev.ralphgonzales.spendlens.shared.constants.ErrorMessages;
import dev.ralphgonzales.spendlens.shared.dto.ApiFieldError;
import dev.ralphgonzales.spendlens.shared.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolations(ConstraintViolationException ex, HttpServletRequest request) {
        List<ApiFieldError> errors = ex.getConstraintViolations().stream()
                        .map(violation-> ApiFieldError.builder()
                                .field(violation.getPropertyPath().toString())
                                .message(violation.getMessage())
                                .build())
                        .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .errors(errors)
                .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex, HttpServletRequest request) {
        List<ApiFieldError> errors = new ArrayList<>();
        Throwable rootCause = ex.getMostSpecificCause();

        if(rootCause instanceof DateTimeParseException) {
            errors.add(ApiFieldError.builder()
                    .field(ErrorMessages.DATE_FIELD)
                    .message(messageSource.getMessage(ErrorMessages.DATE_KEY, null, LocaleContextHolder.getLocale())).build());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .message(messageSource.getMessage(ErrorMessages.FORMAT_ERROR_KEY, null, LocaleContextHolder.getLocale()))
                .errors(errors)
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .message(messageSource.getMessage(ErrorMessages.UNEXPECTED_ERROR_KEY,null, LocaleContextHolder.getLocale()))
                .timestamp(Instant.now())
                .build());
    }
}
