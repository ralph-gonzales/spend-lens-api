package dev.ralphgonzales.spendlens.asset.dto;

import dev.ralphgonzales.spendlens.shared.validation.group.ValidationGroups;
import dev.ralphgonzales.spendlens.shared.validation.annotation.ValidAmount;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssetDto(
        @Null(groups = ValidationGroups.Create.class)
        @NotNull(groups = ValidationGroups.Update.class)
        Long id,
        @NotNull
        LocalDate assetDate,
        @NotNull
        String assetTypeCode,
        Long bankId,
        @ValidAmount
        BigDecimal amount,
        @NotNull
        Long userId
) { }
