package br.com.skeleton.spendsmart.config;


import br.com.skeleton.spendsmart.utils.CacheName;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CaffeineConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CacheName.EXPENSES);
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(10));
    }

//    @Bean
//    public CacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//        cacheManager.registerCustomCache("customers", buildCache(100,200, 2));
//        cacheManager.registerCustomCache("customersSearch", buildCache(50,100, 1));
//        return cacheManager;
//    }
//
//    private Cache buildCache(int initialCapacity, int maximumSize, int durationInHours) {
//        return Caffeine.newBuilder()
//                .initialCapacity(initialCapacity)
//                .maximumSize(maximumSize)
//                .expireAfterAccess(durationInHours, TimeUnit.HOURS)
//                .build();
//    }

}
