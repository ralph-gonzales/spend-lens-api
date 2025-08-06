package dev.ralphgonzales.spendlens.asset.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.asset.validation.annotation.ValidBank;
import dev.ralphgonzales.spendlens.shared.validation.group.ValidationGroups;
import dev.ralphgonzales.spendlens.shared.validation.annotation.ValidAmount;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@ValidBank
public record AssetDto(
        @Null(groups = ValidationGroups.Create.class, message = "{asset.id.null}")
        @NotNull(groups = ValidationGroups.Update.class, message = "{asset.id.required}")
        @Positive(groups = ValidationGroups.Update.class, message = "{asset.id.positive}")
        Long id,
        @NotNull(message="{asset.assetDate.required}")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate assetDate,
        @NotNull(message="{asset.assetTypeCode.required}")
        AssetType assetType,
        Long bankId,
        @ValidAmount
        BigDecimal amount,
        @NotNull(message="{common.userId.required}")
        Long userId
) { }
