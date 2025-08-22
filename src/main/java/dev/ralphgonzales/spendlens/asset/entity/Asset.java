package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDate;

@FieldNameConstants
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "app_user_asset",
        schema = "spend_lens",
        indexes = @Index(name = "ix_app_user_asset__user__date", columnList = "app_user_id, asset_date")
)
public class Asset extends UserOwnedEntity {

    @Column(nullable = false)
    private LocalDate assetDate;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDate assetMonth;

    @Enumerated(value = EnumType.STRING)
    private AssetType assetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Boolean isActive;
}
