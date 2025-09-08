package dev.ralphgonzales.spendlens.cashflow.service;

import dev.ralphgonzales.spendlens.cashflow.dto.CashFlowRequestDto;
import dev.ralphgonzales.spendlens.cashflow.dto.CashFlowResponseDto;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CashFlowServiceImpl implements CashFlowService {
    @Override
    public CashFlowResponseDto create(CashFlowRequestDto dto) {
        return null;
    }

    @Override
    public CashFlowResponseDto update(CashFlowRequestDto dto, Long id) {
        return null;
    }

    @Override
    public CashFlowResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<CashFlowResponseDto> findAll(Pageable pageable) {
        return null;
    }
}
