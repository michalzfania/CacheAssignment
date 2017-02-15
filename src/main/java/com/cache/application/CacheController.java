package com.cache.application;
import com.cache.application.pojos.SingleCacheEntry;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class CacheController {

    @RequestMapping (path = "/cache", method = RequestMethod.POST)
    public void setNewCacheValue(@RequestBody SingleCacheEntry singleCacheEntry) throws IOException {
        CacheFactory.getCache(CacheFactory.eCacheType.PLAIN_CACHE).setEntry(singleCacheEntry.getKey(), singleCacheEntry.getValue());
    }

    @RequestMapping (path = "/cache", method = RequestMethod.GET)
    public List<SingleCacheEntry> getAllCacheEntries() throws IOException {
        return CacheFactory.getCache(CacheFactory.eCacheType.PLAIN_CACHE).getAllEntries();
    }

    @RequestMapping (path = "/cache/{key}", method = RequestMethod.GET)
    public String getValue(@PathVariable String key) throws IOException {
       return CacheFactory.getCache(CacheFactory.eCacheType.PLAIN_CACHE).getValue(key);
    }


    @RequestMapping (path = "/lru/cache", method = RequestMethod.POST)
    public void setLRUNewCacheValue(@RequestBody SingleCacheEntry singleCacheEntry) throws IOException {
        CacheFactory.getCache(CacheFactory.eCacheType.LRU).setEntry(singleCacheEntry.getKey(), singleCacheEntry.getValue());
    }

    @RequestMapping (path = "/lru/cache", method = RequestMethod.GET)
    public List<SingleCacheEntry> getAllLRUCacheEntries() throws IOException {
        return CacheFactory.getCache(CacheFactory.eCacheType.LRU).getAllEntries();
    }

    @RequestMapping (path = "/lru/cache/{key}", method = RequestMethod.GET)
    public String getLRUValue(@PathVariable String key) throws IOException {
        if (key == null || key.isEmpty()){
            return null;
        }
        return CacheFactory.getCache(CacheFactory.eCacheType.LRU).getValue(key);
    }

}
