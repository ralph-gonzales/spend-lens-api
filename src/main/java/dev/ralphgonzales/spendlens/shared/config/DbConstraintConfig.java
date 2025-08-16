package dev.ralphgonzales.spendlens.shared.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DbConstraintProperties.class)
public class DbConstraintConfig {
}
