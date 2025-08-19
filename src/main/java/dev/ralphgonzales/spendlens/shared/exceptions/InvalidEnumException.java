package dev.ralphgonzales.spendlens.shared.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidEnumException extends BaseException{
    public InvalidEnumException(String code, String message, HttpStatus status){
        super(code,message,status);
    }
}
