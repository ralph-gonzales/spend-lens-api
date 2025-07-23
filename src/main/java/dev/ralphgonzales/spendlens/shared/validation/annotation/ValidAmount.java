package dev.ralphgonzales.spendlens.shared.validation.annotation;


import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@DecimalMin(value= "0.0")
@Digits(integer= 10, fraction = 2)
public @interface ValidAmount {
    String message() default "Invalid amount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
