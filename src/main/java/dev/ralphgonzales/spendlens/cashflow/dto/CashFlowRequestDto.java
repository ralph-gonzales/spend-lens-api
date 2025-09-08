package dev.ralphgonzales.spendlens.cashflow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CashFlowRequestDto(
        LocalDate cashFlowDate,
        String cashFlowName,
        String cashFlowTypeCode,
        BigDecimal amount,
        Long userId
) {
}
