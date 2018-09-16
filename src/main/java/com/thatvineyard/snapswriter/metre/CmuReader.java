package com.thatvineyard.snapswriter.metre;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.thatvineyard.snapswriter.files.FileImporter.getResourceAsBufferedReader;

/**
 * CmuReader
 */
public class CmuReader {

    private final static Logger LOGGER = Logger.getLogger(CmuReader.class.getName());

    private static File dictionaryFile;

    private static final String DICTIONARYFILEPATH = "cmudict-0.7b.txt";
    private static final String COMMENTDELIM = ";;;";
    private static final String WORDDELIM = "  ";

    public static CmuDatabase loadDictionary() {
        return loadDictionary(DICTIONARYFILEPATH);
    }

    // TODO: Refactor this into a general solution
    public static CmuDatabase loadDictionary(String filepath) {
        CmuDatabase database = new CmuDatabase();

        BufferedReader bufferedReader = getResourceAsBufferedReader(filepath);
        if(bufferedReader == null) {
            LOGGER.log(Level.SEVERE, "Dictionary file (" + filepath + ") not found.");
        }
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (isValidEntry(line)) {
                    CmuEntry entry = cmuDatabaseLineToCmuEntry(line);

                    database.insertEntry(entry);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return database;

    }

    private static FileReader openDictionaryFile(String filePath) throws FileNotFoundException {
        dictionaryFile = new File(filePath);
        FileReader fileReader = new FileReader(dictionaryFile);

        return fileReader;
    }

    private static boolean isNotEmpty(String line) {
        return line.length() != 0;
    }

    private static boolean isNotComment(String line) {
        String substring = line.substring(0, 3);
        return !substring.equals(COMMENTDELIM);
    }

    private static boolean isValidEntry(String line) {
        return isNotEmpty(line) && isNotComment(line);
    }

    private static CmuEntry cmuDatabaseLineToCmuEntry(String line) {
        int wordStart = 0;
        int wordEnd = line.indexOf(WORDDELIM);
        int pronounciationStart = wordEnd + WORDDELIM.length();
        int pronounciationEnd = line.length();

        String word = line.substring(wordStart, wordEnd);
        String pronounciation = line.substring(pronounciationStart, pronounciationEnd);

        CmuEntry result = new CmuEntry(word, pronounciation);

        return result;
    }

}