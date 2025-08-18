package dev.ralphgonzales.spendlens.shared.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CommonErrorCode implements BaseResponseCode {
    // Asset
    ASSET_RECORD_DUPLICATE("ERR_400_ASSET_001","asset.record.duplicate",HttpStatus.BAD_REQUEST),
    ASSET_TYPE_INVALID("CON_409_ASSET_001", "asset.assetTypeCode.invalid", HttpStatus.CONFLICT),
    ASSET_DB_RECORD_DUPLICATE("CON_409_ASSET_002","asset.record.duplicate",HttpStatus.CONFLICT),

    // Bank
    BANK_ID_INVALID("ERR_400_BANK_001", "common.bankId.invalid", HttpStatus.BAD_REQUEST),

    // Common
    UNEXPECTED_ERROR("ERR_500_COMMON_001", "error.unexpected", HttpStatus.INTERNAL_SERVER_ERROR),
    JSON_INVALID_FORMAT("ERR_400_COMMON_001", "error.format", HttpStatus.BAD_REQUEST),
    DATE_INVALID_FORMAT("ERR_400_COMMON_002", "common.date.format", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_FAILED("ERR_400_COMMON_003", "common.field.failed", HttpStatus.BAD_REQUEST),
    USER_ID_INVALID("CON_409_COMMON_001", "common.userId.invalid", HttpStatus.CONFLICT),
    AMOUNT_BELOW_ZERO("CON_409_COMMON_002", "common.amount.min", HttpStatus.CONFLICT),
    AMOUNT_INVALID_FORMAT("CON_409_COMMON_002", "common.amount.min", HttpStatus.CONFLICT);

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
