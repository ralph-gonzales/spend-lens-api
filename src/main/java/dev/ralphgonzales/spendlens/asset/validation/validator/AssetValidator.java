package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.shared.enums.AssetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Component
@RequiredArgsConstructor
public class AssetValidator {

    private final AssetRepository assetRepository;

    private boolean isDuplicate(AssetDto assetDto){
        AssetType type = AssetType.fromCode(assetDto.assetTypeCode());
        LocalDate startDate = assetDto.assetDate().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = assetDto.assetDate().with(TemporalAdjusters.lastDayOfMonth());

        return switch(type) {
            case CASH -> assetRepository.existsByAssetDateBetween(startDate,endDate);
            case BANK -> assetRepository.existsByBankIdAndAssetDateBetween(assetDto.bankId(), startDate,endDate);
        };
    }

    private boolean isBankExist(AssetDto assetDto){
        return false;
    }
}
