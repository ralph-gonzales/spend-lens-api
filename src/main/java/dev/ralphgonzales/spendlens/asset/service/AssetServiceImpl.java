package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.asset.mapper.AssetMapper;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Override
    public AssetDto create(AssetDto assetDto) {
        return assetMapper.toDto(assetRepository.save(assetMapper.toEntity(assetDto)));
    }

    @Override
    public AssetDto update(AssetDto dto) {
        return null;
    }

    @Override
    public void delete(AssetDto dto) {

    }

    @Override
    public PaginatedResponse<AssetDto> findAll(Pageable pageable) {
        Page<Asset> page = assetRepository.findAll(pageable);

        return PaginatedResponse.<AssetDto>builder()
                .data(assetMapper.toListDto(page.getContent()))
                .currentPage(page.getNumber())
                .isLast(page.isLast())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }
}
