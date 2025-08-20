package dev.ralphgonzales.spendlens.bank.service;

import dev.ralphgonzales.spendlens.bank.dto.BankDto;
import dev.ralphgonzales.spendlens.shared.service.CrudService;

import java.util.List;

public interface BankService extends CrudService<BankDto> {
    List<BankDto> getAllActiveBanks();
}
