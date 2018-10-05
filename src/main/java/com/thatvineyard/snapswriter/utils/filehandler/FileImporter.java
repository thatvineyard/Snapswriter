package com.thatvineyard.snapswriter.utils.filehandler;

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
            return null;
        }

        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static String getFileText(String filepath) {
        try {
            BufferedReader bufferedReader = getResourceAsBufferedReader(filepath);
            String line;
            StringBuilder result = new StringBuilder();
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            bufferedReader.close();
            }
            return result.toString();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
