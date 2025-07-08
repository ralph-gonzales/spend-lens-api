package dev.ralphgonzales.spendlens.asset.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_deposits")
public class TimeDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long bankId;
    private LocalDateTime maturityDate;
    private BigDecimal principalAmount;
    private Long userId;
}
