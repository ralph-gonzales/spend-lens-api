package dev.ralphgonzales.spendlens.shared.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:messages/ValidationMessages",
                "classpath:messages/SuccessMessages"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
