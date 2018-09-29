package com.thatvineyard.snapswriter.metre;

/**
 * Metre
 */
public class Metre {

    private StressSequence stressSequence;
    private int syllables;

    public Metre() {
        this.stressSequence = new StressSequence();
        syllables = stressSequence.getSyllables();
    }

    public Metre(String stressSequence) {
        this.stressSequence = new StressSequence(stressSequence);
        syllables = this.stressSequence.getSyllables();
    }

    public Metre(CmuEntry cmuEntry) {
        this.stressSequence = cmuEntry.getStressSequence();
        syllables = this.stressSequence.getSyllables();
    }

    public void append(Metre other) {
        this.stressSequence.append(other.stressSequence);
        syllables += other.stressSequence.getSyllables();
    }

    public static Metre join(Iterable<Metre> metres) {
        Metre result = new Metre();

        for (Metre metre : metres) {
            result.append(metre);
        }

        return result;
    }

    public String toString() {
        return stressSequence.toString();
    }

    public int getSyllables() {
        return syllables;
    }

    public int compareSyllables(Metre other) {
        return Integer.compare(syllables, other.syllables);
    }

    public boolean equals(Metre other) {
        return compareSyllables(other) == 0 && metreDifference(other) == 0;
    }

    public int metreDifference(Metre other) {
        return stressSequence.stressDifference(other.stressSequence);
    }

}