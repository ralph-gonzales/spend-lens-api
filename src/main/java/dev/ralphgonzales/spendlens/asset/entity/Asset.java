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
import java.util.UUID;

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

    @Column(nullable = false, updatable = false, unique = true, columnDefinition = "uuid")
    private UUID uuid;

    @Column(nullable = false)
    private LocalDate assetDate;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDate assetMonth;

    @Enumerated(value = EnumType.STRING)
    private AssetType assetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "bank_id", insertable = false, updatable = false)
    private Long bankId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @PrePersist
    void prePersist(){
        if(uuid == null) uuid = UUID.randomUUID();
    }

    @Override
    public final boolean equals(Object o){
        if(this == o) return true;
        if (o == null) return false;
        if(getClass() != o.getClass()) return false;

        Asset that = (Asset) o;
        return uuid != null && uuid.equals(that.uuid);
    }

    @Override
    public final int hashCode(){
        return (uuid != null) ? uuid.hashCode() : 0;
    }
}
