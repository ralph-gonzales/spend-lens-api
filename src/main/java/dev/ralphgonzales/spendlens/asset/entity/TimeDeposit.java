package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_deposits")
public class TimeDeposit extends UserOwnedEntity {
    private Long bankId;
    private LocalDateTime maturityDate;
    private BigDecimal principalAmount;
    private Long userId;
}
