package dev.ralphgonzales.spendlens.bank.service;

import dev.ralphgonzales.spendlens.bank.dto.BankDto;
import dev.ralphgonzales.spendlens.shared.service.CrudService;

import java.util.Set;

public interface BankService extends CrudService<BankDto> {
    Set<Long> getAllActiveBanks();
}
