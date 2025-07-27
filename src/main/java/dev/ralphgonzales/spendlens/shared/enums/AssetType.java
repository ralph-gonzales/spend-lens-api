package dev.ralphgonzales.spendlens.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AssetType {
    CASH("CASH", "Cash"),
    BANK("BANK", "Bank");

    private final String code;
    private final String name;

    public static boolean isBank(String code){
        return BANK.getCode().equalsIgnoreCase(code);
    }

    public static AssetType fromCode(String code){
        return Arrays.stream(values())
                .filter(type->type.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Invalid assetTypeCode: " + code));
    }
}
