package com.dou.kylin.security.shiro.redis;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import com.dou.kylin.security.shiro.common.cache.ICache;

public class ShiroRedisCacheManager extends AbstractCacheManager {

    private ICache cache;

    @Override
    protected Cache createCache(String cacheName) throws CacheException {
        return new ShiroRedisCache<String, Object>(cacheName, cache);
    }

    public ICache getCache() {
        return cache;
    }

    public void setCache(ICache cache) {
        this.cache = cache;
    }

}
