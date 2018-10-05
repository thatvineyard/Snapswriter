package com.thatvineyard.snapswriter.metre;

public class WordMetre {

    private StressSequence stressSequence;
    private int syllables;

    public WordMetre() {
        this.stressSequence = new StressSequence();
        syllables = stressSequence.getSyllables();
    }

    public WordMetre(String stressSequence) {
        this.stressSequence = new StressSequence(stressSequence);
        syllables = this.stressSequence.getSyllables();
    }

    public WordMetre(StressSequence stressSequence) {
        this.stressSequence = stressSequence;
        syllables = stressSequence.getSyllables();
    }

    public int getSyllables() {
        return syllables;
    }

    public StressSequence getStressSequence() {
        return stressSequence;
    }

    public int metreDifference(WordMetre other) {
        return stressSequence.stressDifference(other.stressSequence);
    }

    public String toString() {
        return stressSequence.toString();
    }
}
