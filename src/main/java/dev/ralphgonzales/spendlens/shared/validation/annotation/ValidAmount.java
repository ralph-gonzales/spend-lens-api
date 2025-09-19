package dev.ralphgonzales.spendlens.shared.validation.annotation;


import dev.ralphgonzales.spendlens.shared.validation.group.ValidationGroups;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(groups = ValidationGroups.Create.class, message = "{common.amount.required}")
@DecimalMin(value= "0.0", message = "{common.amount.min}")
@Digits(integer= 10, fraction = 2, message = "{common.amount.digits}")
public @interface ValidAmount {
    String message() default "{common.amount.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
