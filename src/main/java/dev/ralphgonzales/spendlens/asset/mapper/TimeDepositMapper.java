package dev.ralphgonzales.spendlens.asset.mapper;

import dev.ralphgonzales.spendlens.asset.dto.TimeDepositRequestDto;
import dev.ralphgonzales.spendlens.asset.entity.TimeDeposit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeDepositMapper {
    TimeDeposit toDto(TimeDepositRequestDto timeDeposit);
    TimeDepositRequestDto toEntity(TimeDeposit timeDeposit);
}
