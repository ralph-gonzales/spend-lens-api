package dev.ralphgonzales.spendlens.shared.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CommonErrorCode implements BaseResponseCode {
    ASSET_DUPLICATE_RECORD("ASSET_DUPLICATE_RECORD","asset.record.duplicate",HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR("UNEXPECTED_ERROR", "error.unexpected", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_JSON_FORMAT("INVALID_JSON_FORMAT", "error.format", HttpStatus.BAD_REQUEST),
    INVALID_DATE_FORMAT("INVALID_DATE_FORMAT", "common.date.format", HttpStatus.BAD_REQUEST),
    INVALID_BANK_ID("INVALID_BANK_ID", "common.bankId.invalid", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_FAILED("FIELD_VALIDATION_FAILED", "common.field.failed", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String messageKey;
    private final HttpStatus status;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
