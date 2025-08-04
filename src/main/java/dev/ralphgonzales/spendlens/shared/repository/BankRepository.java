package dev.ralphgonzales.spendlens.shared.repository;

import dev.ralphgonzales.spendlens.shared.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
