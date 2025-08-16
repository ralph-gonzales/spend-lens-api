package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.asset.mapper.AssetMapper;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import dev.ralphgonzales.spendlens.shared.persistence.constraints.translator.DbConstraintTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;
    private final DbConstraintTranslator dbConstraintTranslator;

    @Override
    @Transactional
    public AssetDto create(AssetDto assetDto) {
        try{
            Asset saved = assetRepository.save(assetMapper.toEntity(assetDto));
            return assetMapper.toDto(saved);
        }catch(DataIntegrityViolationException ex) {
            throw dbConstraintTranslator.map(ex);
        }
    }

    @Override
    public AssetDto update(AssetDto dto) {
        return null;
    }

    @Override
    public AssetDto getById(Long id) { return null;}

    @Override
    public void delete(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<AssetDto> findAll(Pageable pageable) {
        Page<Asset> page = assetRepository.findAll(pageable);

        return new PaginatedResponse<>(
                assetMapper.toListDto(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
