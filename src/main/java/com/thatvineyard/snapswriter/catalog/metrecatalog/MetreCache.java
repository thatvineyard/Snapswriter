package com.thatvineyard.snapswriter.catalog.metrecatalog;


import com.thatvineyard.snapswriter.metre.PassageMetre;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import java.util.HashMap;

@Singleton
public class MetreCache {

    private Logger log = Logger.getLogger(this.getClass());

    private HashMap<String, PassageMetre> PassageMetreMap;

    public MetreCache() {
        PassageMetreMap = new HashMap<>();
    }

    public PassageMetre getPassageMetreById(String PassageMetreId) {
        return PassageMetreMap.get(PassageMetreId);
    }

    public void putPassageMetreInCache(String PassageMetreId, PassageMetre PassageMetre) {
        log.info("Adding " + PassageMetreId + " to cache.");
        PassageMetreMap.put(PassageMetreId, PassageMetre);
    }

}
