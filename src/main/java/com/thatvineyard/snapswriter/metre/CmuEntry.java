package com.thatvineyard.snapswriter.metre;

/**
 * CmuEntry
 */
public class CmuEntry implements Comparable<CmuEntry> {

    private String word;
    private String pronunciation;
    private StressSequence stressSequence;

    // CONSTRUCTORS

    public CmuEntry(String word, String pronunciation, String stressSequence) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.stressSequence = new StressSequence(stressSequence);
    }

    public CmuEntry(String word, String pronunciation) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.stressSequence = pronunciationToStressSequence(pronunciation);
    }

    // VALIDATORS

    private boolean isStressSymbol(char c) {
        return (c == '0' || c == '1' || c == '2');
    }

    // TRANSLATORS

    private StressSequence pronunciationToStressSequence(String pronunciation) {
        int pronunciationLength = pronunciation.length();

        StressSequence stressSequence = new StressSequence();

        for (int i = 0; i < pronunciationLength; i++) {
            if (isStressSymbol(pronunciation.charAt(i))) {
                stressSequence.append(pronunciation.charAt(i));
            }
        }

        return stressSequence;
    }

    // ACCESSORS

    public String getWord() {
        return word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public StressSequence getStressSequence() {
        return stressSequence;
    }

    // COMPARATORS

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