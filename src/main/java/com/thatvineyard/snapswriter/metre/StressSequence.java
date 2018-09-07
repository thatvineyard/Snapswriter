package com.thatvineyard.snapswriter.metre;

import com.thatvineyard.snapswriter.metre.StressSequenceSettings.StressLevel;

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
        } else if (a != -1) {
            return StressSequenceSettings.getStressDifferenceScore(a);
        } else if (b != -1) {
            return StressSequenceSettings.getStressDifferenceScore(b);
        } else {
            return 0;
        }

    }

    private int stressDifferenceCalculation(int a, int b) {
        return StressSequenceSettings.getStressDifferenceScore(a, b);
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