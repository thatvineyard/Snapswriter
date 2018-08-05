package com.thatvineyard.snapswriter.metre;

import java.util.Collection;
import java.util.LinkedList;

/**
 * MetreCalculator
 */
public class MetreCalculator {

    CmuDatabase database;

    public MetreCalculator() {
        loadDatabase();
    }

    public MetreCalculator(String dictionaryFilePath) {
        loadDatabase(dictionaryFilePath);
    }

    public void loadDatabase() {
        database = CmuReader.loadDictionary();
    }

    public void loadDatabase(String dictionaryFilePath) {
        database = CmuReader.loadDictionary(dictionaryFilePath);
    }

    public Metre calculateMetreFromWord(String word) {
        CmuEntry cmuEntry = database.search(word);
        // TODO: Failsafe in case word doesn't exist in dictionary. Use Textgain with no
        // stresses.
        Metre metre;
        if (cmuEntry != null) {
            metre = new Metre(cmuEntry);
        } else {
            metre = new Metre("");
        }

        return metre;
    }

    public Collection<Metre> calculateMetresFromWords(Iterable<String> words) {
        Collection<Metre> metres = new LinkedList<Metre>();

        for (String word : words) {
            metres.add(calculateMetreFromWord(word));
        }

        return metres;
    }

}
