package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.TimeDepositRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.TimeDepositResponseDto;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public class TimeDepositServiceImpl implements TimeDepositService {
    @Override
    public TimeDepositResponseDto create(TimeDepositRequestDto dto) {
        return null;
    }

    @Override
    public TimeDepositResponseDto update(TimeDepositRequestDto dto, Long id) {
        return null;
    }

    @Override
    public TimeDepositResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<TimeDepositResponseDto> findAll(Pageable pageable) {
        return null;
    }
}
