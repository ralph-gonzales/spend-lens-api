package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
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
        return List.of();
    }
}
