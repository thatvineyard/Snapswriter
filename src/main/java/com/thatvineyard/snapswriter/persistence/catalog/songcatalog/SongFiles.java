package com.thatvineyard.snapswriter.persistence.catalog.songcatalog;

import com.thatvineyard.snapswriter.model.format.Formatter;
import com.thatvineyard.snapswriter.model.format.Song;
import com.thatvineyard.snapswriter.persistence.filehandler.File;
import com.thatvineyard.snapswriter.persistence.filehandler.FileImporter;
import com.thatvineyard.snapswriter.persistence.filehandler.FileMapper;

public class SongFiles {

    private static FileMapper songFileMapper;

    static {
        songFileMapper = new FileMapper();
    }

    public static Song getSongById(String songId) {
        File file = songFileMapper.getFile(songId);
        return readFileIntoSong(file);
    }

    public static Song readFileIntoSong(File file) {
        String songString = getFileContents(file.getFilepath());
        switch (file.getFileType()) {
            case RAW:
                return Formatter.stringToSong(songString);
            case JSON:
                return Formatter.jsonToSong(songString);
            default:
                return Formatter.stringToSong(songString);
        }
    }

    public static String getFileContents(String filepath) {
        return FileImporter.readFile(filepath);
    }

    public static String getStringFromFileWithFilePath(String filepath) {
        return FileImporter.readFile(filepath);
    }
}
