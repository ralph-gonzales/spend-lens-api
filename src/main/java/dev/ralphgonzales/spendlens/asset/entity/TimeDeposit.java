package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.common.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_deposits")
public class TimeDeposit extends BaseEntity {
    private Long bankId;
    private LocalDateTime maturityDate;
    private BigDecimal principalAmount;
    private Long userId;
}
