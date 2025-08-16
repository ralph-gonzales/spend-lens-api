package dev.ralphgonzales.spendlens.shared.exceptions;

import org.springframework.http.HttpStatus;

public class DatabaseConstraintException extends BaseException{
    public DatabaseConstraintException(String code, String message, HttpStatus status) {
        super(code, message, status);
    }
}
