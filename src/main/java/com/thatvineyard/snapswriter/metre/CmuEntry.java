package com.thatvineyard.snapswriter.metre;

/**
 * CmuEntry
 */
public class CmuEntry implements Comparable<CmuEntry> {

    private String word;
    private String pronounciation;
    private StressSequence stressSequence;

    public CmuEntry(String word, String pronounciation, String stressSequence) {
        this.word = word;
        this.pronounciation = pronounciation;
        this.stressSequence = new StressSequence(stressSequence);
    }

    public CmuEntry(String word, String pronounciation) {
        this.word = word;
        this.pronounciation = pronounciation;
        this.stressSequence = pronounciationToStressSequence(pronounciation);
    }

    private boolean isStressSymbol(char c) {
        return (c == '0' || c == '1' || c == '2');
    }

    private StressSequence pronounciationToStressSequence(String pronounciation) {
        int pronounciationLength = pronounciation.length();

        StressSequence stressSequence = new StressSequence();

        for (int i = 0; i < pronounciationLength; i++) {
            if (isStressSymbol(pronounciation.charAt(i))) {
                stressSequence.append(pronounciation.charAt(i));
            }
        }

        return stressSequence;
    }

    public String getWord() {
        return word;
    }

    public String getPronouciation() {
        return pronounciation;
    }

    public StressSequence getStressSequence() {
        return stressSequence;
    }

    public int compareTo(CmuEntry other) {
        return word.compareTo(other.word);
    }

    public int compareTo(String other) {
        return word.toLowerCase().compareTo(other.toLowerCase());
    }

    public boolean equals(CmuEntry other) {
        return compareTo(other) == 0;
    }
}