package com.as.lingod.config.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by brander on 2019/1/30
 */
@Configuration
public class LocalCache {
    /**
     * 写入1分钟后过期
     */
    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(60L, TimeUnit.SECONDS)
            .maximumSize(50000L)
            .build();

    public String getCache(String key) {
        return cache.getIfPresent(key);
    }

    public Cache<String, String> getCache() {
        return cache;
    }

    public void setCache(String key, String obj) {
        cache.put(key, obj);
    }

    public void removeCache(String key) {
        cache.invalidate(key);
    }

    public void removeAll() {
        cache.invalidateAll();
    }

    public long getSize() {
        return cache.size();
    }

}
