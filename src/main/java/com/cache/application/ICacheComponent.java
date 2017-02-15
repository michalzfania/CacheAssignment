package com.cache.application;

import com.cache.application.pojos.SingleCacheEntry;

import java.util.List;

/**
 * Created by michal.zfania on 15-Feb-17.
 */
public interface ICacheComponent {
    /**
     * inserts a new enrty to change
     * @param key key to insert
     * @param value value to insert
     */
    void setEntry(String key, String value);

    /**
     * @return list of all key value pairs in cache
     */
    List<SingleCacheEntry> getAllEntries();

    /**
     *
     * @param key
     * @return returns the value of key in cache
     * if key does not exist, returns null;
     */
    String getValue(String key);


}
