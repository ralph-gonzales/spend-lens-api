package dev.ralphgonzales.spendlens.expense.service;

import dev.ralphgonzales.spendlens.expense.dto.ExpenseRequestDto;
import dev.ralphgonzales.spendlens.expense.dto.ExpenseResponseDto;
import dev.ralphgonzales.spendlens.shared.service.CrudService;

public interface ExpenseService extends CrudService<ExpenseRequestDto, ExpenseResponseDto> {
}
