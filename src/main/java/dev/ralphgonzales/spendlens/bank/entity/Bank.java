package dev.ralphgonzales.spendlens.bank.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Entity
@Table(name = "bank", schema = "spend_lens")
public class Bank extends BaseEntity {

    @Id
    Long id;

    @Column(nullable = false, length = 254)
    private String name;

    @Column(insertable = false, updatable = false)
    private String name_normalized;
}
