package dev.ralphgonzales.spendlens.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssetType {
    CASH("CASH", "Cash"),
    BANK("BANK", "Bank");

    private final String code;
    private final String name;

    public static boolean isBank(String code){
        return code.equalsIgnoreCase(BANK.getCode());
    }
}
