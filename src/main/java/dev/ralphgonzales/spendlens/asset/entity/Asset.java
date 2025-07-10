package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset extends UserOwnedEntity {
    private LocalDate assetDate;
    private String assetTypeCode;
    private Long bankId;
    private BigDecimal amount;
    private Long userId;
    private Boolean isActive;
}
