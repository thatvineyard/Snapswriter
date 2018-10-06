package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import org.apache.log4j.Logger;

public class SongDatabase {

    private static Logger log = Logger.getLogger(SongDatabase.class);

    public static Song getSongById(String songId) {
        return null;
    }

    public static void putSong(String songId, Song song) {
        log.info("Adding " + songId + " to database." + " (not implemented yet)");
    }
}
