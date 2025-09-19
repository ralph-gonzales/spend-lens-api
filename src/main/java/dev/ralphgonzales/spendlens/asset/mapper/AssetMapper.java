package dev.ralphgonzales.spendlens.asset.mapper;

import dev.ralphgonzales.spendlens.asset.dto.AssetRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.AssetResponseDto;
import dev.ralphgonzales.spendlens.asset.dto.AssetTypeDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.bank.mapper.BankMapper;
import dev.ralphgonzales.spendlens.bank.service.BankService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = BankMapper.class)
public interface AssetMapper {
    @Mapping(target = "bank", source = "bankId", qualifiedByName = "toBankSummary")
    @Mapping(target = "assetType", source = "assetType", qualifiedByName = "assetTypeEnumToDto")
    AssetResponseDto toResponse(Asset entity, @Context BankService bankService);

    Asset toEntity(AssetRequestDto dto);

    @Mapping(target = "bank", source = "bankId", qualifiedByName = "toBankSummary")
    @Mapping(target = "assetType", source = "assetType", qualifiedByName = "assetTypeEnumToDto")
    List<AssetResponseDto> toResponseList(List<Asset> entities, @Context BankService bankService);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "appUserId", ignore = true)
    void overwriteFromDto(AssetRequestDto dto, @MappingTarget Asset entity);

    @Named("assetTypeEnumToDto")
    static AssetTypeDto assetTypeEnumToDto(AssetType type){
        return (type == null) ? null : new AssetTypeDto(type.code(), type.label());
    }
}