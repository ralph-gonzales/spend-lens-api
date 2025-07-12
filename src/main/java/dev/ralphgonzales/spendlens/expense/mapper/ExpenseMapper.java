package dev.ralphgonzales.spendlens.expense.mapper;

import dev.ralphgonzales.spendlens.expense.dto.ExpenseDto;
import dev.ralphgonzales.spendlens.expense.entity.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toDto(ExpenseDto expenseDto);
    ExpenseDto toEntity(Expense expense);
}
