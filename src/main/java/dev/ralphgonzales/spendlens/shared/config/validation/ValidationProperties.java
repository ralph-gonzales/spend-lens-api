package dev.ralphgonzales.spendlens.shared.config.validation;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "validation")
public record ValidationProperties (Amount amount, Date date){
    public record Amount (BigDecimal min, Digits digits){
        public record Digits(int integer, int fraction){ }
    }
    public record Date(String format){}
}
