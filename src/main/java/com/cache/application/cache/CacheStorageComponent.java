package com.cache.application.cache;

import com.cache.application.ICacheComponent;
import com.cache.application.pojos.SingleCacheEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michal.zfania on 13-Feb-17.
 */
public class CacheStorageComponent implements ICacheComponent {

    private static Map<String, String> cacheStorage = new HashMap<>();
    private static CacheStorageComponent instance = new CacheStorageComponent();

    public static CacheStorageComponent getInstance(){
        return instance;
    }

    private CacheStorageComponent() {
    }

    public void setEntry(String key, String value){
        if (key != null && value != null) {
            cacheStorage.put(key, value);
        }
    }

    public List<SingleCacheEntry> getAllEntries(){
        List<SingleCacheEntry> cacheList =  new ArrayList<>();
        for (Map.Entry<String,String> singleEntryInCache :  cacheStorage.entrySet()){
            cacheList.add(new SingleCacheEntry(singleEntryInCache.getKey(), singleEntryInCache.getValue()));
        }

        return cacheList;
    }

    public String getValue(String key){
        if (key == null){
            return null;
        }

        return cacheStorage.get(key);
    }
}
