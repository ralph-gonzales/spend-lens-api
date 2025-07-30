package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.shared.constants.ErrorMessages;
import dev.ralphgonzales.spendlens.shared.enums.AssetType;
import dev.ralphgonzales.spendlens.asset.validation.annotation.ValidBank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@RequiredArgsConstructor
public class BankValidator implements ConstraintValidator<ValidBank, AssetDto> {

    private final MessageSource messageSource;

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

        if(assetDto.bankId() < 0 ){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    messageSource.getMessage(ErrorMessages.INVALID_BANK_KEY,null, LocaleContextHolder.getLocale()))
                    .addPropertyNode(BANK_ID_FIELD)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
