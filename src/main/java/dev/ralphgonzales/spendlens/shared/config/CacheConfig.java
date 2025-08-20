package dev.ralphgonzales.spendlens.shared.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public CacheManager cacheManager(){
        var manager = new CaffeineCacheManager();

        manager.registerCustomCache("banks",
            Caffeine.newBuilder()
                    .maximumSize(256)
                    .build());

        return manager;
    }
}
