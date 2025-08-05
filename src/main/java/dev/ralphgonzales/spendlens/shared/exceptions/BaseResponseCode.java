package dev.ralphgonzales.spendlens.shared.exceptions;

import org.springframework.http.HttpStatus;

public interface BaseResponseCode {
    String getCode();
    String getMessageKey();
    HttpStatus getStatus();
}
