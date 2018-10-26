package com.thatvineyard.snapswriter.persistence.catalog.abstractcatalog;


import org.apache.log4j.Logger;

import java.util.HashMap;

public abstract class AbstractCache<T> {

    private Logger log = Logger.getLogger(this.getClass());

    private HashMap<String, T> cacheMap;

    public AbstractCache() {
        cacheMap = new HashMap<>();
    }

    public T getContentByKey(String key) {
        return cacheMap.get(key);
    }

    public void putContentInCache(String key, T value) {
        log.info("Adding " + key + " to cache.");
        cacheMap.put(key, value);
    }

}
