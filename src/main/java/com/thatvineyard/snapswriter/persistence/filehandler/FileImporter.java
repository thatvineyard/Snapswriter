package com.thatvineyard.snapswriter.persistence.filehandler;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * FileReader
 */
public class FileImporter {

    private static final Logger log = Logger.getLogger(FileImporter.class);

    public static String readFile(String filepath) {
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
            log.debug(result);
            return result.toString();

        } catch (IOException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static BufferedReader getResourceAsBufferedReader(String filepath) throws IOException {
        InputStream inputStream = FileImporter.class.getClassLoader().getResourceAsStream(filepath);

        if (inputStream == null) {
            throw new IOException("Could not get inputstream from filepath");
        }

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(reader);
    }
}
