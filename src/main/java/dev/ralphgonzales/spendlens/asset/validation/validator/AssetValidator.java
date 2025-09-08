package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.asset.specification.AssetSpecification;
import dev.ralphgonzales.spendlens.bank.service.BankService;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.exceptions.BusinessValidationException;
import dev.ralphgonzales.spendlens.shared.persistence.jpa.SpecificationUtil;
import dev.ralphgonzales.spendlens.shared.validation.contract.BusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AssetValidator implements BusinessValidator<AssetDto> {

    private final AssetRepository assetRepository;
    private final BankService bankService;
    private final MessageResolver messageResolver;

    @Override
    public void createValidate(AssetDto assetDto){
        final Locale locale = LocaleContextHolder.getLocale();

        validateUniqueness(assetDto, locale);
        validateBank(assetDto, locale);
    }

    @Override
    public void updateValidate(AssetDto assetDto){

    }

    private void validateUniqueness(AssetDto assetDto, Locale locale){
        AssetType type = assetDto.assetType();
        LocalDate startDate = assetDto.assetDate().with(TemporalAdjusters.firstDayOfMonth());

        Specification<Asset> spec = null;
        spec = SpecificationUtil.and(spec, AssetSpecification.isUserIdEqual(assetDto.userId()));
        spec = SpecificationUtil.and(spec, AssetSpecification.isAssetMonthEquals(startDate));
        spec = SpecificationUtil.and(spec, AssetSpecification.isAssetTypeEquals(type.name()));
        if(AssetType.BANK == type){
            spec = SpecificationUtil.and(spec, AssetSpecification.isBankIdEquals(assetDto.bankId()));
        }

        if(!assetRepository.findAll(spec).isEmpty()){
            throw new BusinessValidationException(CommonErrorCode.ASSET_DB_RECORD_DUPLICATE.getCode(),
                    messageSource.getMessage(CommonErrorCode.ASSET_DB_RECORD_DUPLICATE.getMessageKey(),null,locale),
                    CommonErrorCode.ASSET_DB_RECORD_DUPLICATE.getStatus());
        }
    }

    private void validateBank(AssetDto assetDto, Locale locale){
        if(AssetType.BANK == assetDto.assetType()){
            Set<Long> bankIds = bankService.getAllActiveBanks();
            boolean isValid = bankIds.contains(request.bankId());

            if(isValid){
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
