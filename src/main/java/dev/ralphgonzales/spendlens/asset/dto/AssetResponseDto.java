package dev.ralphgonzales.spendlens.asset.dto;

import dev.ralphgonzales.spendlens.bank.dto.BankSummaryDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record AssetResponseDto(
        Long id,
        LocalDate assetDate,
        BankSummaryDto bank,
        AssetTypeDto assetType,
        BigDecimal amount,
        Long userId,
        Long version,
        Boolean isActive,
        OffsetDateTime createdDate,
        OffsetDateTime lastModifiedDate
) {
}
