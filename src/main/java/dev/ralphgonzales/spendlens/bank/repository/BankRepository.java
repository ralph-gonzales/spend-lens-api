package dev.ralphgonzales.spendlens.bank.repository;

import dev.ralphgonzales.spendlens.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("select b.id from Bank b where b.isActive = true")
    Set<Long> findIdsByActiveTrue();
}
