package dev.ralphgonzales.spendlens.shared.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@FieldNameConstants
@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    @LastModifiedBy
    private Long lastModifiedBy;

    @LastModifiedDate
    private Instant lastModifiedDate;

    public void deactivate() {
        this.isActive = false;
    }
}
