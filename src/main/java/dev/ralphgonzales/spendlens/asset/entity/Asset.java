package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.shared.domain.Bank;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Asset extends UserOwnedEntity {
    private LocalDate assetDate;
    @Enumerated(EnumType.STRING)
    private AssetType assetType;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    private BigDecimal amount;
    private Long userId;
    private Boolean isActive;
}
