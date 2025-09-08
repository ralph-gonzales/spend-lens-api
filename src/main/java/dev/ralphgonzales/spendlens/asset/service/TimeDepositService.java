package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.TimeDepositRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.TimeDepositResponseDto;
import dev.ralphgonzales.spendlens.shared.service.CrudService;

public interface TimeDepositService extends CrudService<TimeDepositRequestDto, TimeDepositResponseDto> {
}
