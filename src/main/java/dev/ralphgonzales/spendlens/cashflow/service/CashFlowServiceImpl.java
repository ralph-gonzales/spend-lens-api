package dev.ralphgonzales.spendlens.cashflow.service;

import dev.ralphgonzales.spendlens.cashflow.dto.CashFlowDto;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CashFlowDto> findAll() {
        return List.of();
    }
}
