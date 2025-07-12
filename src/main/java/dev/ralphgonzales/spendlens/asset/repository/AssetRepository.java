package dev.ralphgonzales.spendlens.asset.repository;

import dev.ralphgonzales.spendlens.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {
}
