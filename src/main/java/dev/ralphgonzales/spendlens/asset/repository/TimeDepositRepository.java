package dev.ralphgonzales.spendlens.asset.repository;

import dev.ralphgonzales.spendlens.asset.entity.TimeDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDepositRepository extends JpaRepository<TimeDeposit,Long> {
}
