package dev.ralphgonzales.spendlens.bank.mapper;

import dev.ralphgonzales.spendlens.bank.dto.BankDto;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankDto toDto(Bank entity);
    Bank toEntity(BankDto dto);
    List<BankDto> toDtos(List<Bank> entities);
}
