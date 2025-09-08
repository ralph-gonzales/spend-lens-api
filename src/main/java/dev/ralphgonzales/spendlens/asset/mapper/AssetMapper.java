package dev.ralphgonzales.spendlens.asset.mapper;

import dev.ralphgonzales.spendlens.asset.dto.AssetRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.AssetResponseDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.bank.mapper.BankMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = BankMapper.class)
public interface AssetMapper {
    @Mapping(target = "bank", source = "bank", qualifiedByName = "toBankSummary")
    AssetResponseDto toResponse(Asset entity);

    Asset toEntity(AssetRequestDto dto);

    @Mapping(target = "bank", source = "bank", qualifiedByName = "toBankSummary")
    List<AssetResponseDto> toResponseList(List<Asset> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "appUserId", ignore = true)
    void overwriteFromDto(AssetRequestDto dto, @MappingTarget Asset entity);
}