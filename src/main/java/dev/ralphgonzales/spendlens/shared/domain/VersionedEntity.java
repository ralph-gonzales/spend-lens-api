package dev.ralphgonzales.spendlens.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@MappedSuperclass
@Getter
@NoArgsConstructor
@FieldNameConstants
public abstract class VersionedEntity extends UserOwnedEntity{
    @Version
    @Column(nullable = false)
    private Long version;
}
