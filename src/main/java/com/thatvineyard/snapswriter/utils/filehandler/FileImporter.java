package com.thatvineyard.snapswriter.utils.filehandler;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * FileReader
 */
public class FileImporter {

    private static Logger log = Logger.getLogger(FileImporter.class);

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
            return result.toString();

        } catch (IOException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static BufferedReader getResourceAsBufferedReader(String filepath) throws IOException {
        InputStream inputStream = FileImporter.class.getClassLoader().getResourceAsStream(filepath);

        if(inputStream == null) {
            throw new IOException("Could not get inputstream from filepath");
        }

        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
