package dev.ralphgonzales.spendlens.asset.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long assetId;

    private LocalDate date;
    private String assetTypeCode;
    private Long bankId;
    private BigDecimal amount;
    private Long userId;
    private Boolean isActive;
}
