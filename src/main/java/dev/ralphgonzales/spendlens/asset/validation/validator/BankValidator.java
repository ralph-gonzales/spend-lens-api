package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.shared.enums.AssetType;
import dev.ralphgonzales.spendlens.asset.validation.annotation.ValidBank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BankValidator implements ConstraintValidator<ValidBank, AssetDto> {

    static final String BANK_ID_FIELD = "bankId";

    private String message;

    @Override
    public void initialize(ValidBank constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(AssetDto assetDto, ConstraintValidatorContext constraintValidatorContext) {
        if(AssetType.isBank(assetDto.assetTypeCode()) && assetDto.bankId() == null){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(BANK_ID_FIELD)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
