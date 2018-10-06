package com.thatvineyard.snapswriter.metre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WordMetre implements WordMetreInterface {

    private StressSequence stressSequence;
    private int syllables;

    public WordMetre() {
        this.stressSequence = new StressSequence();
        syllables = stressSequence.getSyllables();
    }

    public WordMetre(WordMetreInterface other) {
        this.stressSequence = other.getStressSequence();
        this.syllables = other.getSyllables();
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

    @JsonIgnore
    public StressSequence getStressSequence() {
        return stressSequence;
    }

    public int metreDifference(MetreInterface other) {
        return stressSequence.stressDifference(other.getStressSequence());
    }

    @JsonProperty("stress-sequence")
    public String toString() {
        return stressSequence.toString();
    }
}
