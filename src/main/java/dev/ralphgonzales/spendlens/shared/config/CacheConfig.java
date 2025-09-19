package dev.ralphgonzales.spendlens.shared.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    public CacheManager cacheManager(){
        SimpleCacheManager man = new SimpleCacheManager();

        CaffeineCache banks = new CaffeineCache(
                "banks",
                Caffeine.newBuilder()
                .maximumSize(256)
                .expireAfterWrite(Duration.ofHours(24))
                .recordStats()
                .build()
        );

        CaffeineCache bankSummaryById = new CaffeineCache(
                "bankSummaryById",
                Caffeine.newBuilder()
                .maximumSize(256)
                .expireAfterWrite(Duration.ofHours(24))
                .recordStats()
                .build()
        );

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(banks, bankSummaryById));

        return manager;
    }
}
