package com.cache.application;

import com.cache.application.cache.CacheStorageComponent;
import com.cache.application.lruCache.LRUCacheStorageComponent;

/**
 * Created by michal.zfania on 15-Feb-17.
 */
public class CacheFactory {

    public static ICacheComponent getCache(eCacheType cacheType){
        switch (cacheType){
            case LRU:
                return LRUCacheStorageComponent.getInstance();
            case PLAIN_CACHE:
                return CacheStorageComponent.getInstance();
            default:
                return CacheStorageComponent.getInstance();
        }
    }

    public enum eCacheType{
        LRU,
        PLAIN_CACHE
    }

}
