package com.thatvineyard.snapswriter.catalog.metrecatalog;

import com.thatvineyard.snapswriter.metre.PassageMetre;
import com.thatvineyard.snapswriter.metre.calculator.MetreCalculator;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MetreCatalog {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    MetreCache metreCache;

    public PassageMetre getPassageMetre(String PassageMetreId) {
        PassageMetre result;

        result = getPassageMetreFromCache(PassageMetreId);

        if (result == null) {
            result = getPassageMetreFromDatabase(PassageMetreId);

            if (result == null) {
                result = getPassageMetreFromFileSystem(PassageMetreId);

                if (result == null) {
                    log.info(PassageMetreId + " not found");

                    return null;
                } else {
                    log.info(PassageMetreId + " found in file system");
                }
                putPassageMetreInDatabase(PassageMetreId, result);
            } else {
                log.info(PassageMetreId + " found in database");
            }

            putPassageMetreInCache(PassageMetreId, result);
        } else {
            log.info(PassageMetreId + " found in cache");
        }

        return result;
    }

    private PassageMetre getPassageMetreFromCache(String PassageMetreId) {
        return metreCache.getContentByKey(PassageMetreId);
    }

    public void putPassageMetreInCache(String PassageMetreId, PassageMetre PassageMetre) {
        metreCache.putContentInCache(PassageMetreId, PassageMetre);
    }

    private static PassageMetre getPassageMetreFromDatabase(String PassageMetreId) {
        //return SongDatabase.getContentByKey(PassageMetreId);
        return null;
    }

    private static void putPassageMetreInDatabase(String PassageMetreId, PassageMetre PassageMetre) {
        //SongDatabase.putSong(PassageMetreId, PassageMetre);
    }

    private static PassageMetre getPassageMetreFromFileSystem(String PassageMetreId) {
        return MetreFiles.getPassageMetreById(PassageMetreId);
    }

}
