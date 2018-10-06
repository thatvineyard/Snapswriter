package com.thatvineyard.snapswriter.catalog.passagecatalog;

import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import java.util.HashMap;

@Singleton
public class PassageCache {

    private Logger log = Logger.getLogger(this.getClass());

    private HashMap<String, Passage> passageMap;

    public PassageCache() {
        passageMap = new HashMap<>();
    }

    public Passage getPassageById(String passageId) {
        return passageMap.get(passageId);
    }

    public void putPassageInCache(String passageId, Passage passage) {
        log.info("Adding " + passageId + " to cache.");
        passageMap.put(passageId, passage);
    }

}
