package dev.ralphgonzales.spendlens.asset.validation.validator;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.enums.AssetType;
import dev.ralphgonzales.spendlens.asset.validation.annotation.ValidBank;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.i18n.MessageResolver;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;

@RequiredArgsConstructor
public class BankValidator implements ConstraintValidator<ValidBank, AssetDto> {

    private final MessageResolver messageResolver;

    static final String BANK_ID_FIELD = "bankId";

    private String message;

    @Override
    public void initialize(ValidBank constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(AssetDto assetDto, ConstraintValidatorContext constraintValidatorContext) {
        if(AssetType.BANK == assetDto.assetType() && assetDto.bankId() == null){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(BANK_ID_FIELD)
                    .addConstraintViolation();
            return false;
        }

        if(assetDto.bankId() < 0 ){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            messageResolver.getMessage(CommonErrorCode.BANK_ID_INVALID.getMessageKey()))
                    .addPropertyNode(BANK_ID_FIELD)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
