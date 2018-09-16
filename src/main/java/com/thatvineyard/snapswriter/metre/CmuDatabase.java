package com.thatvineyard.snapswriter.metre;

import java.util.TreeSet;

/**
 * CmuDatabase
 */
public class CmuDatabase {

    private TreeSet<CmuEntry> set;

    public CmuDatabase() {
        set = new TreeSet<CmuEntry>();
    }

    public void insertEntry(CmuEntry entry) {
        set.add(entry);
    }

    public CmuEntry search(String word) {
        CmuEntry result = getWordOrGreaterFromSet(word);

        if (result != null && result.compareTo(word) == 0) {
            return result;
        } else {
            return null;
        }
    }

    private CmuEntry getWordOrGreaterFromSet(String word) {
        return set.ceiling(new CmuEntry(word.toUpperCase(), ""));
    }

    public int size() {
        return set.size();
    }

}