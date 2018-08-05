package com.thatvineyard.snapswriter.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * FileReader
 */
public class FileImporter {

    public static String getFileText(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line + "\n";
            }
            bufferedReader.close();
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}