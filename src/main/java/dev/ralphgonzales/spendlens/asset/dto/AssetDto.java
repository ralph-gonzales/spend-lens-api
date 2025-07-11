package dev.ralphgonzales.spendlens.asset.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssetDto(
    LocalDate assetDate,
    String assetTypeCode,
    Long bankId,
    BigDecimal amount,
    Long userId
) { }
