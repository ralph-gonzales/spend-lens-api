package dev.ralphgonzales.spendlens.asset.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TimeDepositDto(
        Long bankId,
        LocalDate maturityDate,
        BigDecimal principalAmount,
        Long userId
) {
}
