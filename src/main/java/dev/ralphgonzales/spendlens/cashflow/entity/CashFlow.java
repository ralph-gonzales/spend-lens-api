package dev.ralphgonzales.spendlens.cashflow.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_flows")
public class CashFlow extends UserOwnedEntity {
    private LocalDate cashFlowDate;
    private String cashFlowName;
    private String cashFlowTypeCode;
    private BigDecimal amount;
    private Long userId;
}
