package com.thatvineyard.snapswriter.metre;

/**
 * StressSequence
 */
public class StressSequence {

    private String sequence;

    private static final int DIFFERENTLENGTHDIFFERENCESCORE = 5;

    public StressSequence() {
        this.sequence = "";
    }

    public StressSequence(String sequence) {
        this.sequence = sequence;
    }

    public String toString() {
        return sequence;
    }

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
        } else {
            return DIFFERENTLENGTHDIFFERENCESCORE;
        }

    }

    private int stressDifferenceCalculation(int a, int b) {
        if (a == b) {
            return 0;
        }
        if ((a == 1 && b == 0) || (a == 0 && b == 1)) {
            return 2;
        }
        if ((a == 2 && b == 1) || (a == 1 && b == 2)) {
            return 1;
        }
        if ((a == 2 && b == 0) || (a == 0 && b == 2)) {
            return 1;
        }
        return 0;
    }

    public int length() {
        return sequence.length();
    }

    public boolean sameLengthAs(StressSequence other) {
        return length() == other.length();
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

    public int getSyllables() {
        return sequence.length();
    }
}