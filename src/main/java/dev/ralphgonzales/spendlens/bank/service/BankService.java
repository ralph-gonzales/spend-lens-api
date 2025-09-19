package dev.ralphgonzales.spendlens.bank.service;

import dev.ralphgonzales.spendlens.bank.dto.BankRequestDto;
import dev.ralphgonzales.spendlens.bank.dto.BankResponseDto;
import dev.ralphgonzales.spendlens.bank.dto.BankSummaryDto;
import dev.ralphgonzales.spendlens.shared.service.CrudService;

import java.util.Set;

public interface BankService extends CrudService<BankRequestDto, BankResponseDto> {
    Set<Long> getAllActiveBanks();
    BankSummaryDto getSummaryById(Long id);
}
