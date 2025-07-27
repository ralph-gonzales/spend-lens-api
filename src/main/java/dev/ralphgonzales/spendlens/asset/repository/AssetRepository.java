package dev.ralphgonzales.spendlens.asset.repository;

import dev.ralphgonzales.spendlens.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    boolean existsByAssetDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByBankIdAndAssetDateBetween(Long bankId, LocalDate startDate, LocalDate endDate);
}
