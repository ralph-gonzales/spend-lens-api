package dev.ralphgonzales.spendlens.asset.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssetDto(
    @NotNull
    @NotBlank
    LocalDate assetDate,
    @NotNull
    @NotBlank
    String assetTypeCode,
    Long bankId,
    @NotNull
    @NotBlank
    @PositiveOrZero
    @Digits(integer= 9 , fraction = 2)
    BigDecimal amount,
    @NotNull
    @NotBlank
    Long userId
) { }
