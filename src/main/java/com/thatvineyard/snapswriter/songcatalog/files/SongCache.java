package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Song;

import javax.ejb.Singleton;
import java.util.HashMap;

@Singleton
public class SongCache {

    private HashMap<String, Song> songMap;

    public SongCache() {
        songMap = new HashMap<>();
    }

    public Song getSongById(String songId) {
        return songMap.get(songId);
    }

    public void putSongInCache(String songId, Song song) {
        songMap.put(songId, song);
    }

}
