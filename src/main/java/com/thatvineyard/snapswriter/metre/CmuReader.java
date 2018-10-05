package com.thatvineyard.snapswriter.metre;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.thatvineyard.snapswriter.utils.filehandler.FileImporter.getResourceAsBufferedReader;

/**
 * CmuReader
 */
public class CmuReader {

    private final static Logger LOGGER = Logger.getLogger(CmuReader.class.getName());

    private static File dictionaryFile;

    private static final String DICTIONARY_FILEPATH = "cmudict-0.7b.txt";
    private static final String COMMENT_DELIM = ";;;";
    private static final String WORD_DELIM = "  ";

    // FACTORIES

    public static CmuDatabase loadDictionary() {
        return loadDictionaryFromFile(DICTIONARY_FILEPATH);
    }

    // TODO: Refactor this into a general solution
    public static CmuDatabase loadDictionaryFromFile(String filepath) {
        BufferedReader bufferedReader = getResourceAsBufferedReader(filepath);
        if(bufferedReader == null) {
            LOGGER.log(Level.SEVERE, "Dictionary file (" + filepath + ") not found.");
        }

        CmuDatabase database = readLinesIntoDatabase(bufferedReader, new CmuDatabase());

        return database;
    }

    // UTILITIES

    private static CmuDatabase readLinesIntoDatabase(BufferedReader bufferedReader, CmuDatabase database) {
        String line;

        try {
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    if (isValidEntry(line)) {
                        CmuEntry entry = toCmuEntry(line);

                        database.insertEntry(entry);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return database;
    }

    // VALIDATORS

    private static boolean isNotEmpty(String line) {
        return line.length() != 0;
    }

    private static boolean isNotComment(String line) {
        String substring = line.substring(0, 3);
        return !substring.equals(COMMENT_DELIM);
    }

    private static boolean isValidEntry(String line) {
        return isNotEmpty(line) && isNotComment(line);
    }

    // TRANSLATORS

    private static CmuEntry toCmuEntry(String line) {
        int wordStart = 0;
        int wordEnd = line.indexOf(WORD_DELIM);
        int pronunciationStart = wordEnd + WORD_DELIM.length();
        int pronunciationEnd = line.length();

        String word = line.substring(wordStart, wordEnd);
        String pronunciation = line.substring(pronunciationStart, pronunciationEnd);

        return new CmuEntry(word, pronunciation);
    }

}
