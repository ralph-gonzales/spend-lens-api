package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetRequestDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.asset.specification.AssetSpecification;
import dev.ralphgonzales.spendlens.bank.service.BankService;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.exceptions.BusinessValidationException;
import dev.ralphgonzales.spendlens.shared.i18n.MessageResolver;
import dev.ralphgonzales.spendlens.shared.validation.contract.BusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AssetValidator implements BusinessValidator<AssetRequestDto, Asset> {

    private final AssetRepository assetRepository;
    private final BankService bankService;
    private final MessageResolver messageResolver;

    @Override
    public void createValidate(AssetRequestDto assetDto){
        validateUniqueness(assetDto, false, null);
        validateBank(assetDto);
    }

    @Override
    public void updateValidate(AssetRequestDto assetDto, Asset existing){
        validateUniqueness(assetDto, true, existing);
        validateBank(assetDto);
        validateVersion(assetDto, existing);
    }

    private void validateUniqueness(AssetRequestDto request, boolean isUpdate, Asset existing){
        CommonErrorCode error = CommonErrorCode.ASSET_DB_RECORD_DUPLICATE;

        // TODO: Replace with proper implementation for userId
        Long appUserId = 1L;
        String assetType = request.assetType() != null ? request.assetType() : existing.getAssetType().code();
        LocalDate startDate = (request.assetDate() != null
                ? request.assetDate()
                : existing.getAssetMonth()
        ).with(TemporalAdjusters.firstDayOfMonth());
        Long bankId = null;

        if(Objects.equals(AssetType.BANK.code(), assetType)){
            bankId = request.bankId() != null ? request.bankId() : existing.getBankId();
        }

        if(isUpdate && Objects.equals(appUserId, existing.getAppUserId())
            && Objects.equals(assetType, existing.getAssetType().code())
            && Objects.equals(startDate, existing.getAssetMonth())
            && Objects.equals(bankId, existing.getBankId())){
            return;
        }

        Specification<Asset> spec = (root, query, cb) -> cb.conjunction();
        spec = spec
                .and(AssetSpecification.isUserIdEqual(appUserId))
                .and(AssetSpecification.isAssetMonthEquals(startDate))
                .and(AssetSpecification.isAssetTypeEquals(assetType))
                .and(AssetSpecification.isActive());

        if(Objects.equals(AssetType.BANK.code(), assetType)){
            spec = spec.and(AssetSpecification.isBankIdEquals(bankId));
        }else{
            spec = spec.and(AssetSpecification.isBankIdNull());
        }
        if(isUpdate){
            spec = spec.and(Specification.not(AssetSpecification.isIdEquals(existing.getId())));
        }

        boolean exists = assetRepository.exists(spec);

        if(exists){
            throw new BusinessValidationException(error.getCode(),
                    messageResolver.getMessage(error.getMessageKey()),
                    error.getStatus());
        }
    }

    private void validateBank(AssetRequestDto request){
        if(Objects.equals(AssetType.BANK.code(), request.assetType())){
            CommonErrorCode error = CommonErrorCode.BANK_ID_INVALID;
            Set<Long> bankIds = bankService.getAllActiveBanks();
            boolean isValid = bankIds.contains(request.bankId());

            if(!isValid){
                throw new BusinessValidationException(error.getCode(),
                        messageResolver.getMessage(error.getMessageKey()),
                        error.getStatus());
            }
        }
    }

    private void validateVersion(AssetRequestDto request, Asset entity){
        if(!Objects.equals(request.version(), entity.getVersion())){
            CommonErrorCode error = CommonErrorCode.VERSION_CONFLICT;
            throw new BusinessValidationException(error.getCode(),
                    messageResolver.getMessage(error.getMessageKey()), error.getStatus());
        }
    }
}
