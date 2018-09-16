package com.thatvineyard.snapswriter.files;

import com.thatvineyard.snapswriter.metre.CmuReader;

import java.io.*;
import java.util.logging.Logger;

/**
 * FileReader
 */
public class FileImporter {

    private final static Logger LOGGER = Logger.getLogger(FileImporter.class.getName());

    public static BufferedReader getResourceAsBufferedReader(String filepath) {
        InputStream inputStream = FileImporter.class.getClassLoader().getResourceAsStream(filepath);
        if(inputStream == null) {
            LOGGER.severe("File not found (" + filepath + ").");
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader;
    }

    public static String getFileText(String filepath) {
        try {
            BufferedReader bufferedReader = getResourceAsBufferedReader(filepath);
            String line;
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line + "\n";
            }
            bufferedReader.close();
            return result;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}