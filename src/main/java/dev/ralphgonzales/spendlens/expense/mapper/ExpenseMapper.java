package dev.ralphgonzales.spendlens.expense.mapper;

import dev.ralphgonzales.spendlens.expense.dto.ExpenseRequestDto;
import dev.ralphgonzales.spendlens.expense.entity.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toDto(ExpenseRequestDto expenseDto);
    ExpenseRequestDto toEntity(Expense expense);
}
