package dev.ralphgonzales.spendlens.cashflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_flows")
public class CashFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate date;
    private String name;
    private String cashFlowTypeCode;
    private BigDecimal amount;
    private Long userId;
}
