package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Song;

public class SongDatabase {

    public static Song getSongById(String songId) {
        return SongFiles.getSongById(songId);
    }

}
