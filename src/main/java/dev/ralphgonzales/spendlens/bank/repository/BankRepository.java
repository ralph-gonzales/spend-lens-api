package dev.ralphgonzales.spendlens.bank.repository;

import dev.ralphgonzales.spendlens.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Set<Long> findIdsByActiveTrue();
}
