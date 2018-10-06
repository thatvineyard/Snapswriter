package com.thatvineyard.snapswriter.catalog.passagecatalog;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.utils.filehandler.File;
import com.thatvineyard.snapswriter.utils.filehandler.FileImporter;
import com.thatvineyard.snapswriter.utils.filehandler.FileMapper;

public class PassageFiles {

    private static FileMapper songFileMapper;

    static {
        songFileMapper = new FileMapper();
    }

    public static Passage getPassageById(String passageId) {
        File file = songFileMapper.getFile(passageId);
        return readFileIntoPassage(file);
    }

    public static Passage readFileIntoPassage(File file) {
        String passageString = getFileContents(file.getFilepath());
        switch (file.getFileType()) {
            case RAW:
                return Formatter.stringToPassage(passageString);
            case JSON:
                //return Formatter.jsonToSong(passageString);
            default:
                return Formatter.stringToPassage(passageString);
        }
    }

    public static String getFileContents(String filepath) {
        return FileImporter.readFile(filepath);
    }

    public static String getStringFromFileWithFilePath(String filepath) {
        return FileImporter.readFile(filepath);
    }
}
