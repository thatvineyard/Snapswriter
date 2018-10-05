package com.thatvineyard.snapswriter.songcatalog.files;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.utils.filehandler.FileImporter;
import com.thatvineyard.snapswriter.utils.filehandler.FileMapper;

public class SongFiles {

    private static FileMapper songFileMapper;

    static {
        songFileMapper = new FileMapper();
    }

    public static Song getSongById(String songId) {
        String songString = getFileContentsById(songId);
        Passage songText = Formatter.stringToPassage(songString);
        return new Song("", songText);
    }

    public static String getFileContentsById(String songId) {
        String filepath = songFileMapper.getFilepath(songId);

        return FileImporter.readFile(filepath);
    }

    public static String getStringFromFileWithFilePath(String filepath) {
        return FileImporter.readFile(filepath);
    }
}
