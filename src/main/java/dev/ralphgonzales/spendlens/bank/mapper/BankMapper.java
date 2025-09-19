package dev.ralphgonzales.spendlens.bank.mapper;

import dev.ralphgonzales.spendlens.bank.dto.BankRequestDto;
import dev.ralphgonzales.spendlens.bank.dto.BankSummaryDto;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import dev.ralphgonzales.spendlens.bank.service.BankService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankRequestDto toDto(Bank entity);
    Bank toEntity(BankRequestDto dto);
    List<BankRequestDto> toDtos(List<Bank> entities);

    @Named("toBankSummary")
    static BankSummaryDto toBankSummary(Long bankId, @Context BankService bankService){
        return (bankId == null) ? null : bankService.getSummaryById(bankId);
    }
}
