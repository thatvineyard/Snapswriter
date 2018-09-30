package com.thatvineyard.snapswriter.metre;

import java.util.Collection;
import java.util.LinkedList;

/**
 * MetreCalculator
 */
public class MetreCalculator {

    CmuDatabase database;

    private boolean useCmuDatabase = true;
    private boolean useTextgain = true;

    public MetreCalculator() {
        loadDatabase();
    }

    public MetreCalculator(String dictionaryFilePath) {
        loadDatabase(dictionaryFilePath);
    }

    // SETTINGS

    public void useCmuDatabase(boolean value) {
        useCmuDatabase = value;
    }

    public void useTextgain(boolean value) {
        useTextgain = value;
    }

    // MUTATORS

    public void loadDatabase() {
        database = CmuReader.loadDictionary();
    }

    public void loadDatabase(String dictionaryFilePath) {
        database = CmuReader.loadDictionaryFromFile(dictionaryFilePath);
    }

    // ACCESSOR

    public Metre calculateMetreFromWord(String word) {
        Metre metre = null;

        if (useCmuDatabase) {
            CmuEntry cmuEntry = database.search(word);
            if (cmuEntry != null) {
                metre = new Metre(cmuEntry);
            }
        }

        if (useTextgain) {
           if(metre == null) {
               metre = new Metre(TextgainReader.numberOfSyllablesInString(word));
            }
        }

        if (metre == null) {
            metre = new Metre();
        }

        return metre;
    }

    public Collection<Metre> calculateMetresFromWords(Iterable<String> words) {
        Collection<Metre> metres = new LinkedList<>();

        for (String word : words) {
            metres.add(calculateMetreFromWord(word));
        }

        return metres;
    }

}
