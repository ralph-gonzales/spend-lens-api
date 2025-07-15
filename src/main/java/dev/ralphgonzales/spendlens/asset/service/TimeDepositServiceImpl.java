package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.TimeDepositDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TimeDepositServiceImpl implements TimeDepositService {
    @Override
    public TimeDepositDto create(TimeDepositDto dto) {
        return null;
    }

    @Override
    public TimeDepositDto update(TimeDepositDto dto) {
        return null;
    }

    @Override
    public void delete(TimeDepositDto dto) {

    }

    @Override
    public List<TimeDepositDto> findAll(Pageable pageable) {
        return List.of();
    }
}
