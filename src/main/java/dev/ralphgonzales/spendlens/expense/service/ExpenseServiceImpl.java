package dev.ralphgonzales.spendlens.expense.service;

import dev.ralphgonzales.spendlens.expense.dto.ExpenseDto;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Override
    public ExpenseDto create(ExpenseDto dto) {
        return null;
    }

    @Override
    public ExpenseDto update(ExpenseDto dto) {
        return null;
    }

    @Override
    public void delete(ExpenseDto dto) {

    }

    @Override
    public PaginatedResponse<ExpenseDto> findAll(Pageable pageable) {
        return null;
    }
}
