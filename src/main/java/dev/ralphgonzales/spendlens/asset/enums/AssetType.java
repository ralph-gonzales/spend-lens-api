package dev.ralphgonzales.spendlens.asset.enums;

public enum AssetType {
    CASH("CASH","Cash"),
    BANK("BANK","Traditional and Digital Banks");

    private final String code;
    private final String label;

    AssetType(String code, String label){
        this.code = code;
        this.label = label;
    }

    public String code(){ return code; }
    public String label() { return label; }
}
