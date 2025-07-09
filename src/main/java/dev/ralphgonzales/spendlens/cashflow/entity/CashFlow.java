package dev.ralphgonzales.spendlens.cashflow.entity;

import dev.ralphgonzales.spendlens.common.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_flows")
public class CashFlow extends BaseEntity {
    private LocalDate date;
    private String name;
    private String cashFlowTypeCode;
    private BigDecimal amount;
    private Long userId;
}
