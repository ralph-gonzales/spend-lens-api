package dev.ralphgonzales.spendlens.asset.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.ralphgonzales.spendlens.bank.dto.BankSummaryDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AssetResponseDto(
        Long id,
        LocalDate assetDate,
        BankSummaryDto bank,
        AssetTypeDto assetType,
        BigDecimal amount,
        Long version,
        Boolean isActive,
        Instant createdDate,
        Instant lastModifiedDate
) {
}
