package com.thatvineyard.snapswriter.utils.filehandler;

import java.util.HashMap;
import java.util.Map;

public class FileMapper {


    private static final String resourceDir            = "";
    private static final String cmuDictFilePath = resourceDir + "cmudict-0.7b.txt";
    private static final String cmuDictSeFilePath = resourceDir + "cmudict-svenska-0.1.txt";
    private static final String allStarRawPath         = resourceDir + "all-star.txt";
    private static final String allStarJsonPath        = resourceDir + "all-star.json";
    private static final String communismPath          = resourceDir + "communism-wiki.txt";
    private static final String navyPath               = resourceDir + "navy.txt";
    private static final String communistNavyPath      = resourceDir + "communistnavy.txt";
    private static final String helanGarPath      = resourceDir + "helan-går.txt";


    private Map<String, File> fileMap;

    public FileMapper() {
        fileMap = new HashMap<>();
        addDefaultFiles();
    }

    private void addDefaultFiles() {
        addFile("dictionary", File.FileType.RAW, cmuDictFilePath);
        addFile("dictionary-se", File.FileType.RAW, cmuDictSeFilePath);
        addFile("all-star", File.FileType.RAW, allStarRawPath);
        addFile("all-star-json", File.FileType.JSON, allStarJsonPath);
        addFile("communism", File.FileType.RAW, communismPath);
        addFile("navy", File.FileType.RAW, navyPath);
        addFile("communistnavy", File.FileType.RAW, communistNavyPath);
        addFile("helan-gar", File.FileType.RAW, helanGarPath);
    }

    public void addFile(String fileId, File.FileType fileType, String filepath) {
        fileMap.put(fileId, new File(fileId, fileType, filepath));
    }

    public File getFile(String filename) {
        return fileMap.get(filename);
    }
}
