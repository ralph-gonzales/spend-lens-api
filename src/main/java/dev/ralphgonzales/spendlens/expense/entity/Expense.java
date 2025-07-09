package dev.ralphgonzales.spendlens.expense.entity;

import dev.ralphgonzales.spendlens.common.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense extends BaseEntity {
    private String name;
    private LocalDate date;
    private String expenseTypeCode;
    private String expenseCategoryCode;
    private String paymentTypeCode;
    private BigDecimal amount;
    private Long userId;
}
