package com.thatvineyard.snapswriter.metre;

/**
 * StressSequence
 */
public class StressSequence {

    private String sequence;

    // CONSTRUCTORS

    public StressSequence() {
        this.sequence = "";
    }

    public StressSequence(String sequence) {
        this.sequence = sequence;
    }

    public StressSequence(int syllables) {
        this.sequence = syllablesToSequence(syllables);
    }

    // FACTORIES

    public String syllablesToSequence(int syllables) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < syllables; i++) {
            builder.append(0);
        }
        return builder.toString();
    }

    // FORMATTERS

    public String toString() {
        return sequence;
    }

    // COMPARATORS

    public int stressDifference(StressSequence other) {
        int sequenceLength = length();
        int difference = 0;

        for (int i = 0; i < sequenceLength; i++) {
            difference += stressDifferenceAtIndex(other, i);
        }

        return difference;

    }

    private int stressDifferenceAtIndex(StressSequence other, int i) {
        int a = getStressValueAtIndex(i);
        int b = other.getStressValueAtIndex(i);

        if (a != -1 && b != -1) {
            return stressDifferenceCalculation(a, b);
        } else if (a != -1) {
            return StressSequenceSettings.getStressDifferenceScore(a);
        } else if (b != -1) {
            return StressSequenceSettings.getStressDifferenceScore(b);
        } else {
            return 0;
        }

    }

    public boolean sameLengthAs(StressSequence other) {
        return length() == other.length();
    }

    // UTILITIES

    private int stressDifferenceCalculation(int a, int b) {
        return StressSequenceSettings.getStressDifferenceScore(a, b);
    }

    // ACCESSORS

    public int length() {
        return sequence.length();
    }

    private int getStressValueAtIndex(int i) {
        int value;

        try {
            char character = sequence.charAt(i);

            value = Integer.parseInt(Character.toString(character));
        } catch (IndexOutOfBoundsException e) {
            value = -1;
        }

        return value;
    }

    public int getSyllables() {
        return sequence.length();
    }

    // MUTATORS

    public void append(StressSequence other) {
        sequence = sequence.concat(other.sequence);
    }

    public void append(String other) {
        sequence += other;
    }

    public void append(Character other) {
        sequence += other;
    }

    public static StressSequence join(Iterable<StressSequence> sequenceList) {
        StressSequence result = new StressSequence("");

        for (StressSequence sequence : sequenceList) {
            result.append(sequence);
        }

        return result;
    }

}
