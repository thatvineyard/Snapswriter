package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Song;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SongCatalog {

    @Inject
    SongCache songCache;

    public Song getSong(String songId) {
        Song result;

        result = getSongFromCache(songId);

        if(result == null) {
            result = getSongFromDatabase(songId);

            if(result == null) {
                result = getSongFromFileSystem(songId);

                if (result == null) {

                    return null;
                }

                putSongInDatabase(result);
            }

            putSongInCache(result);
        }

        return result;
    }

    private Song getSongFromCache(String songId) {
        return songCache.getSongById(songId);
    }

    private static void putSongInCache(Song song) {
        // LOGIC
    }

    private static Song getSongFromDatabase(String songId) {
        return SongDatabase.getSongById(songId);
    }

    private static void putSongInDatabase(Song song) {
        // LOGIC
    }

    private static Song getSongFromFileSystem(String songId) {
        return SongFiles.getSongById(songId);
    }

}
