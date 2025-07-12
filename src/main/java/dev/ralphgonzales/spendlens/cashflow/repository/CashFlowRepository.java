package dev.ralphgonzales.spendlens.cashflow.repository;

import dev.ralphgonzales.spendlens.cashflow.entity.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow,Long> {
}
