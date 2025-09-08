package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.AssetRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.AssetResponseDto;
import dev.ralphgonzales.spendlens.shared.service.CrudService;

public interface AssetService extends CrudService<AssetRequestDto, AssetResponseDto> {
}
