package com.thatvineyard.snapswriter.model.metre.calculator;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static com.thatvineyard.snapswriter.persistence.filehandler.FileImporter.getResourceAsBufferedReader;

/**
 * CmuReader
 */
public class CmuReader {

    private static Logger log = Logger.getLogger(CmuReader.class);

    private static File dictionaryFile;

    private static final String DEFAULT_DICTIONARY_FILEPATH = "cmudict-0.7b.txt";
    private static final String COMMENT_DELIM = ";;;";
    private static final String WORD_DELIM = "  ";

    // FACTORIES

    public static CmuDatabase loadDictionary() {
        return loadDictionaryFromFile(DEFAULT_DICTIONARY_FILEPATH);
    }

    // TODO: Refactor this into a general solution
    public static CmuDatabase loadDictionaryFromFile(String filepath) {
        return loadDictionaryFromFile(filepath, new CmuDatabase());
    }

    private static CmuDatabase loadDictionaryFromFile(String filepath, CmuDatabase cmuDatabase) {
        try {
            BufferedReader bufferedReader = getResourceAsBufferedReader(filepath);
            CmuDatabase database = readLinesIntoDatabase(bufferedReader, cmuDatabase);
            return database;
        } catch (IOException e) {
            log.fatal(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static CmuDatabase loadDictionariesFromFile(Collection<String> filepaths) {
        CmuDatabase cmuDatabase = new CmuDatabase();
        for (String filepath : filepaths) {
            cmuDatabase = loadDictionaryFromFile(filepath, cmuDatabase);
        }
        return cmuDatabase;
    }

    // UTILITIES

    private static CmuDatabase readLinesIntoDatabase(BufferedReader bufferedReader, CmuDatabase database) throws IOException {
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (isValidEntry(line)) {
                CmuEntry entry = toCmuEntry(line);
                database.insertEntry(entry);
            }
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

        if (wordEnd == -1) {
            throw new RuntimeException("Dictionary formatting error in line [" + line + "]: Found no word delimiter (" + WORD_DELIM + ")");
        }

        String word = line.substring(wordStart, wordEnd);
        String pronunciation = line.substring(pronunciationStart, pronunciationEnd);

        return new CmuEntry(word, pronunciation);
    }

}
