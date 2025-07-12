package dev.ralphgonzales.spendlens.expense.repository;

import dev.ralphgonzales.spendlens.asset.entity.TimeDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<TimeDeposit,Long> {
}
