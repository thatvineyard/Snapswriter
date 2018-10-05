package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Song;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SongCatalog {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    SongCache songCache;

    public Song getSong(String songId) {
        Song result;

        result = getSongFromCache(songId);

        if (result == null) {
            result = getSongFromDatabase(songId);

            if (result == null) {
                result = getSongFromFileSystem(songId);

                if (result == null) {
                    log.info(songId + " not found");

                    return null;
                } else {
                    log.info(songId + " found in file system");
                }
                putSongInDatabase(songId, result);
            } else {
                log.info(songId + " found in database");
            }

            putSongInCache(songId, result);
        } else {
            log.info(songId + " found in cache");
        }

        return result;
    }

    private Song getSongFromCache(String songId) {
        return songCache.getSongById(songId);
    }

    private void putSongInCache(String songId, Song song) {
        songCache.putSongInCache(songId, song);
    }

    private static Song getSongFromDatabase(String songId) {
        return SongDatabase.getSongById(songId);
    }

    private static void putSongInDatabase(String songId, Song song) {
        SongDatabase.putSong(songId, song);
    }

    private static Song getSongFromFileSystem(String songId) {
        return SongFiles.getSongById(songId);
    }

}
