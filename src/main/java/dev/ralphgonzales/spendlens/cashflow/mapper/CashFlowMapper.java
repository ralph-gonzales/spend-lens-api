package dev.ralphgonzales.spendlens.cashflow.mapper;

import dev.ralphgonzales.spendlens.cashflow.dto.CashFlowRequestDto;
import dev.ralphgonzales.spendlens.cashflow.entity.CashFlow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashFlowMapper {
    CashFlow toDto(CashFlowRequestDto cashFlowDto);
    CashFlowRequestDto toEntity(CashFlow cashFlow);
}
