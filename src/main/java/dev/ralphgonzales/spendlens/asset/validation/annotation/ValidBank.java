package dev.ralphgonzales.spendlens.asset.validation.annotation;

import dev.ralphgonzales.spendlens.asset.validation.validator.BankValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BankValidator.class)
public @interface ValidBank {
    String message() default "{common.bankId.required}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
