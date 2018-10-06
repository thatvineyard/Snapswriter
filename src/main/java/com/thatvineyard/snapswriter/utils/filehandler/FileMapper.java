package com.thatvineyard.snapswriter.utils.filehandler;

import java.util.HashMap;
import java.util.Map;

public class FileMapper {


    private static final String resourceDir = "";
    private static final String testDictionaryFilePath = resourceDir + "cmudict-0.7b.txt";
    private static final String allStarPath = resourceDir + "all-star.txt";
    private static final String communismPath = resourceDir + "communism-wiki.txt";
    private static final String navyPath = resourceDir + "navy.txt";

    private Map<String, String> fileMap;

    public FileMapper() {
        fileMap = new HashMap<>();
        addDefaultFiles();
    }

    private void addDefaultFiles() {
        addFile("dictionary", testDictionaryFilePath);
        addFile("all-star", allStarPath);
        addFile("communism", communismPath);
        addFile("navy", navyPath);
    }

    public void addFile(String filename, String filepath) {
        fileMap.put(filename, filepath);
    }

    public String getFilepath(String filename) {
        return fileMap.get(filename);
    }
}
