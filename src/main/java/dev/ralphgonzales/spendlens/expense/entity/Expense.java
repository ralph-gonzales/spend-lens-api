package dev.ralphgonzales.spendlens.expense.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense extends UserOwnedEntity {
    private String expenseName;
    private LocalDate expenseDate;
    private String expenseTypeCode;
    private String expenseCategoryCode;
    private String paymentTypeCode;
    private BigDecimal amount;
    private Long userId;
}
