package dev.ralphgonzales.spendlens.shared.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "app.db-constraints")
public record DbConstraintProperties(
        Map<String, String> appUser,
        Map<String, String> cashFlow,
        Map<String, String> bank,
        Map<String, String> asset,
        Map<String, String> expense,
        Map<String, String> timeDeposit,
        Map<String, String> other
) {
}
