package dev.ralphgonzales.spendlens.asset.mapper;

import dev.ralphgonzales.spendlens.asset.dto.TimeDepositDto;
import dev.ralphgonzales.spendlens.asset.entity.TimeDeposit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeDepositMapper {
    TimeDeposit toDto(TimeDepositDto timeDeposit);
    TimeDepositDto toEntity(TimeDeposit timeDeposit);
}
