package dev.ralphgonzales.spendlens.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessValidationException extends RuntimeException{

    private static final String BUSINESS_VALIDATION_FAILED = "Business validation failed";

    private final String code;
    private final String message;
    private final HttpStatus status;

    public BusinessValidationException(String code, String message, HttpStatus status){
        super(BUSINESS_VALIDATION_FAILED);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
