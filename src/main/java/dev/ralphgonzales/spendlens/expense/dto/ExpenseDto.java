package dev.ralphgonzales.spendlens.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(
        String expenseName,
        LocalDate expenseDate,
        String expenseTypeCode,
        String expenseTypeCategory,
        String paymentTypeCode,
        BigDecimal amount,
        Long userId
) {
}
