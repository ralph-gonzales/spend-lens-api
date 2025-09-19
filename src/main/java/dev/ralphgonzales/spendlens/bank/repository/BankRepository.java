package dev.ralphgonzales.spendlens.bank.repository;

import dev.ralphgonzales.spendlens.bank.dto.BankSummaryDto;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("select b.id from Bank b where b.isActive = true")
    Set<Long> findIdsByActiveTrue();

    @Query("""
            select new dev.ralphgonzales.spendlens.bank.dto.BankSummaryDto(b.id, b.name)
            from Bank b
            where b.id = :id
            """)
    Optional<BankSummaryDto> findSummaryById(@Param("id") Long id);
}
