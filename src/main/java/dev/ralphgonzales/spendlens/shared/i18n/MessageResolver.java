package dev.ralphgonzales.spendlens.shared.i18n;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageResolver {
    private final MessageSource messageSource;

    public String getMessage(String key, Object... args){
        try{
            return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            log.warn("Missing message key: {}", key);
            return key;
        }
    }
}
