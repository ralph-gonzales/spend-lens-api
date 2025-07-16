package dev.ralphgonzales.spendlens.cashflow.service;

import dev.ralphgonzales.spendlens.cashflow.dto.CashFlowDto;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CashFlowServiceImpl implements CashFlowService {
    @Override
    public CashFlowDto create(CashFlowDto dto) {
        return null;
    }

    @Override
    public CashFlowDto update(CashFlowDto dto) {
        return null;
    }

    @Override
    public void delete(CashFlowDto dto) {

    }

    @Override
    public PaginatedResponse<CashFlowDto> findAll(Pageable pageable) {
        return null;
    }
}
