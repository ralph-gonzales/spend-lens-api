package dev.ralphgonzales.spendlens.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class UserOwnedEntity extends BaseEntity {
    @Column(nullable = false, updatable = false)
    private Long userId;
}
