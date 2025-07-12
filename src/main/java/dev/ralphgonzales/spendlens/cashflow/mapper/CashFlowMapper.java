package dev.ralphgonzales.spendlens.cashflow.mapper;

import dev.ralphgonzales.spendlens.cashflow.dto.CashFlowDto;
import dev.ralphgonzales.spendlens.cashflow.entity.CashFlow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashFlowMapper {
    CashFlow toDto(CashFlowDto cashFlowDto);
    CashFlowDto toEntity(CashFlow cashFlow);
}
