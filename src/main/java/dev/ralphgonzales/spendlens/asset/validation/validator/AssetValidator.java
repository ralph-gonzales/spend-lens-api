package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.exceptions.BusinessValidationException;
import dev.ralphgonzales.spendlens.shared.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Component
@RequiredArgsConstructor
public class AssetValidator {

    private final AssetRepository assetRepository;
    private final BankRepository bankRepository;
    private final MessageSource messageSource;

    public void validateRecord(AssetDto assetDto){
        if(isDuplicate(assetDto)){
            throw new BusinessValidationException(CommonErrorCode.ASSET_DUPLICATE_RECORD.getCode(),
                    messageSource.getMessage(CommonErrorCode.ASSET_DUPLICATE_RECORD.getMessageKey(),null,LocaleContextHolder.getLocale()),
                    CommonErrorCode.ASSET_DUPLICATE_RECORD.getStatus());
        }
    }

    private boolean isDuplicate(AssetDto assetDto){
        AssetType type = assetDto.assetType();
        LocalDate startDate = assetDto.assetDate().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = assetDto.assetDate().with(TemporalAdjusters.lastDayOfMonth());

        return switch(type) {
            case CASH -> assetRepository.existsByAssetDateBetween(startDate,endDate);
            case BANK -> assetRepository.existsByBankIdAndAssetDateBetween(assetDto.bankId(), startDate,endDate);
        };
    }

    private boolean isBankExist(AssetDto assetDto){
        return bankRepository.findById(assetDto.bankId()).isPresent();
    }
}
