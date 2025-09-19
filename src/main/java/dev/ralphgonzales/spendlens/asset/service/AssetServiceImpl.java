package dev.ralphgonzales.spendlens.asset.service;

import dev.ralphgonzales.spendlens.asset.dto.AssetRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.AssetResponseDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.asset.mapper.AssetMapper;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.asset.validation.validator.AssetValidator;
import dev.ralphgonzales.spendlens.bank.entity.Bank;
import dev.ralphgonzales.spendlens.bank.repository.BankRepository;
import dev.ralphgonzales.spendlens.bank.service.BankService;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.exceptions.BusinessValidationException;
import dev.ralphgonzales.spendlens.shared.i18n.MessageResolver;
import dev.ralphgonzales.spendlens.shared.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper mapper;
    private final AssetValidator validator;
    private final BankRepository bankRepository;
    private final BankService bankService;
    private final MessageResolver messageResolver;

    @Transactional
    @Override
    @Validated(ValidationGroups.Create.class)
    public AssetResponseDto create(@Valid AssetRequestDto request) {
        validator.createValidate(request);

        Asset entity = mapper.toEntity(request);

        if(request.bankId() != null){
            Bank bank = bankRepository.findById(request.bankId()).orElseThrow(() -> {
                CommonErrorCode ec = CommonErrorCode.BANK_NOT_EXIST;
                return new BusinessValidationException(ec.getCode(),messageResolver.getMessage(ec.getMessageKey()),
                        ec.getStatus());
            });
            entity.setBank(bank);
        }

        // TODO: implement proper implementation by JWT
        entity.setAppUserId(1L);

        Asset saved = assetRepository.save(entity);

        return mapper.toResponse(saved, bankService);
    }

    @Transactional
    @Override
    @Validated(ValidationGroups.UpdateWithDefault.class)
    public AssetResponseDto update(@Valid AssetRequestDto request, Long id) {
        Asset existing = assetRepository.findById(id).orElseThrow(() -> {
            CommonErrorCode ec = CommonErrorCode.ASSET_NOT_EXIST;
            return new BusinessValidationException(ec.getCode(), messageResolver.getMessage(ec.getMessageKey()),
                    ec.getStatus());
        });

        validator.updateValidate(request, existing);

        mapper.overwriteFromDto(request, existing);

        assetRepository.saveAndFlush(existing);

        return mapper.toResponse(existing, bankService);
    }

    @Override
    public AssetResponseDto getById(Long id) { return null;}

    @Override
    public void delete(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<AssetResponseDto> findAll(Pageable pageable) {
        Page<Asset> page = assetRepository.findAll(pageable);

        return new PaginatedResponse<>(
                mapper.toResponseList(page.getContent(), bankService),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
