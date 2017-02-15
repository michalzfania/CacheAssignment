package com.cache.application.pojos;

/**
 * Created by michal.zfania on 13-Feb-17.
 */
public class SingleCacheEntry {
    String key;
    String value;

    public SingleCacheEntry() {
    }

    public SingleCacheEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
