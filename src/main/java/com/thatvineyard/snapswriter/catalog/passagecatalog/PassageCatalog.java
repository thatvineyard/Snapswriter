package com.thatvineyard.snapswriter.catalog.passagecatalog;

import com.thatvineyard.snapswriter.format.Passage;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PassageCatalog {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    PassageCache passageCache;

    public Passage getPassage(String passageId) {
        Passage result;

        result = getPassageFromCache(passageId);

        if (result == null) {
            result = getPassageFromDatabase(passageId);

            if (result == null) {
                result = getPassageFromFileSystem(passageId);

                if (result == null) {
                    log.info(passageId + " not found");

                    return null;
                } else {
                    log.info(passageId + " found in file system");
                }
                putPassageInDatabase(passageId, result);
            } else {
                log.info(passageId + " found in database");
            }

            putPassageInCache(passageId, result);
        } else {
            log.info(passageId + " found in cache");
        }

        return result;
    }

    private Passage getPassageFromCache(String passageId) {
        return passageCache.getPassageById(passageId);
    }

    public void putPassageInCache(String passageId, Passage passage) {
        passageCache.putPassageInCache(passageId, passage);
    }

    private static Passage getPassageFromDatabase(String passageId) {
        //return SongDatabase.getPassageById(passageId);
        return null;
    }

    private static void putPassageInDatabase(String passageId, Passage passage) {
        //SongDatabase.putSong(passageId, passage);
    }

    private static Passage getPassageFromFileSystem(String passageId) {
        return PassageFiles.getPassageById(passageId);
    }

}
