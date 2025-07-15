package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.mapper.AssetMapper;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Override
    public AssetDto create(AssetDto dto) {
        return null;
    }

    @Override
    public AssetDto update(AssetDto dto) {
        return null;
    }

    @Override
    public void delete(AssetDto dto) {

    }

    @Override
    public List<AssetDto> findAll() {
        return assetMapper.toListDto(assetRepository.findAll());
    }
}
