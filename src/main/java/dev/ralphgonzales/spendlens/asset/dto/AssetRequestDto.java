package dev.ralphgonzales.spendlens.asset.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.ralphgonzales.spendlens.asset.validation.annotation.ValidBank;
import dev.ralphgonzales.spendlens.shared.validation.group.ValidationGroups;
import dev.ralphgonzales.spendlens.shared.validation.annotation.ValidAmount;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@ValidBank
public record AssetRequestDto(
        @NotNull(message="{asset.assetDate.required}")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate assetDate,

        @NotNull(message="{asset.assetTypeCode.required}")
        @Size(max=10, message = "{asset.assetTypeCode.length}")
        String assetType,

        Long bankId,

        @ValidAmount
        BigDecimal amount,

        @NotNull(message="{common.userId.required}")
        Long userId,

        @NotNull(groups = ValidationGroups.Update.class, message = "{common.version.required}")
        @Positive(groups = ValidationGroups.Update.class, message = "{common.version.positive}")
        @Null(groups = ValidationGroups.Create.class, message = "{common.version.null}")
        Long version
) { }
