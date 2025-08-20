package dev.ralphgonzales.spendlens.bank.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Entity
@Table(name = "banks")
public class Bank extends BaseEntity {
    private String name;
    private String name_normalized;
}
