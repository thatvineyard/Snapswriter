package com.thatvineyard.snapswriter.persistence.catalog.songcatalog;

import com.thatvineyard.snapswriter.model.format.Song;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import java.util.HashMap;

@Singleton
public class SongCache {

    private Logger log = Logger.getLogger(this.getClass());

    private HashMap<String, Song> songMap;

    public SongCache() {
        songMap = new HashMap<>();
    }

    public Song getSongById(String songId) {
        return songMap.get(songId);
    }

    public void putSongInCache(String songId, Song song) {
        log.info("Adding " + songId + " to cache.");
        songMap.put(songId, song);
    }

}
