package com.thatvineyard.snapswriter.catalog.abstractcatalog;


import com.thatvineyard.snapswriter.metre.PassageMetre;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import java.util.HashMap;

@Singleton
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
