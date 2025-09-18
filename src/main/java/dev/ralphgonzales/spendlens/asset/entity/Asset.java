package dev.ralphgonzales.spendlens.asset.entity;

import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import dev.ralphgonzales.spendlens.shared.domain.VersionedEntity;
import jakarta.persistence.*;
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
@Entity
@Table(
        name = "app_user_asset",
        schema = "spend_lens",
        indexes = @Index(name = "ix_app_user_asset__user__date", columnList = "app_user_id, asset_date")
)
@SequenceGenerator(
        name = "asset_seq",
        sequenceName = "spend_lens.app_user_asset_id_seq",
        allocationSize = 50
)
public class Asset extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_seq")
    private Long id;

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
}
