package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Song;

public class SongCatalog {


    public static Song getSong(String songId) {
        return SongFiles.getSongFromFileWithSongId(songId);
    }


}
