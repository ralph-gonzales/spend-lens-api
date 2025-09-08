package dev.ralphgonzales.spendlens.expense.service;

import dev.ralphgonzales.spendlens.expense.dto.ExpenseRequestDto;
import dev.ralphgonzales.spendlens.expense.dto.ExpenseResponseDto;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Override
    public ExpenseResponseDto create(ExpenseRequestDto dto) {
        return null;
    }

    @Override
    public ExpenseResponseDto update(ExpenseRequestDto dto, Long id) {
        return null;
    }

    @Override
    public ExpenseResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<ExpenseResponseDto> findAll(Pageable pageable) {
        return null;
    }
}
