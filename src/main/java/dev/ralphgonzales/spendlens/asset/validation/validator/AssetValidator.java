package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.repository.AssetRepository;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.shared.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Component
@RequiredArgsConstructor
public class AssetValidator {

    private final AssetRepository assetRepository;
    private final BankRepository bankRepository;

    public void validateRecord(AssetDto assetDto){

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
