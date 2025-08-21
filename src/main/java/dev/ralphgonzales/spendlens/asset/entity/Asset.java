package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDate;

@FieldNameConstants
@Entity
public class Asset extends UserOwnedEntity {
    private LocalDate assetDate;
    private LocalDate assetMonth;
    @Enumerated(EnumType.STRING)
    private AssetType assetType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
    private BigDecimal amount;
    private Long userId;
    private Boolean isActive;
}
