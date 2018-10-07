package com.thatvineyard.snapswriter.catalog.metrecatalog;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.metre.PassageMetre;
import com.thatvineyard.snapswriter.utils.filehandler.File;
import com.thatvineyard.snapswriter.utils.filehandler.FileImporter;
import com.thatvineyard.snapswriter.utils.filehandler.FileMapper;

public class MetreFiles {

    private static FileMapper songFileMapper;

    static {
        songFileMapper = new FileMapper();
    }

    public static PassageMetre getPassageMetreById(String PassageMetreId) {
        File file = songFileMapper.getFile(PassageMetreId);
        return readFileIntoPassageMetre(file);
    }

    public static PassageMetre readFileIntoPassageMetre(File file) {
        String PassageMetreString = getFileContents(file.getFilepath());
        switch (file.getFileType()) {
            case RAW:
//                return Formatter.stringToPassageMetre(PassageMetreString);
            case JSON:
                //return Formatter.jsonToSong(PassageMetreString);
            default:
//                return Formatter.stringToPassageMetre(PassageMetreString);
        }
        return new PassageMetre();
    }

    public static String getFileContents(String filepath) {
        return FileImporter.readFile(filepath);
    }

    public static String getStringFromFileWithFilePath(String filepath) {
        return FileImporter.readFile(filepath);
    }
}
