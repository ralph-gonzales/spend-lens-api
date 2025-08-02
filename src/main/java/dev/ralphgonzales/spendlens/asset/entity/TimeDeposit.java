package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TimeDeposit extends UserOwnedEntity {
    private Long bankId;
    private LocalDateTime maturityDate;
    private BigDecimal principalAmount;
    private Long userId;
}
