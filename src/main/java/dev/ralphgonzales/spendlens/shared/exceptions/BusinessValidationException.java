package dev.ralphgonzales.spendlens.shared.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessValidationException extends BaseException{

    public BusinessValidationException(String code, String message, HttpStatus status){
        super(code, message, status);
    }
}
