package dev.ralphgonzales.spendlens.asset.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.exceptions.InvalidEnumException;

public enum AssetType {
    CASH,BANK;

    @JsonCreator
    public static AssetType from(String value) {
        if(value == null) {
            throw new InvalidEnumException(
                    CommonErrorCode.ASSET_TYPE_INVALID.getCode(),
                    CommonErrorCode.ASSET_TYPE_INVALID.getMessageKey(),
                    CommonErrorCode.ASSET_TYPE_INVALID.getStatus());
        }
        return switch(value){
            case "BANK" -> BANK;
            case "CASH" -> CASH;
            default -> throw new InvalidEnumException(
                    CommonErrorCode.ASSET_TYPE_INVALID.getCode(),
                    CommonErrorCode.ASSET_TYPE_INVALID.getMessageKey(),
                    CommonErrorCode.ASSET_TYPE_INVALID.getStatus());
        };
    }
}
