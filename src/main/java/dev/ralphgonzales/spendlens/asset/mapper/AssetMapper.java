package dev.ralphgonzales.spendlens.asset.mapper;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    Asset toDto(AssetDto assetDto);
    AssetDto toEntity(Asset asset);
}
