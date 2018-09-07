package com.thatvineyard.snapswriter.metre;

/**
 * StressSequenceSettings
 */
public abstract class StressSequenceSettings {

    private static int PRIMARY_PRIMARY_SCORE = 0;
    private static int PRIMARY_SECONDARY_SCORE = 1;
    private static int PRIMARY_NONE_SCORE = 2;
    private static int PRIMARY_EMPTY_SCORE = 5;
    private static int SECONDARY_NONE_SCORE = 1;
    private static int SECONDARY_EMPTY_SCORE = 5;
    private static int NONE_EMPTY_SCORE = 3;

    public enum StressLevel {
        PRIMARY(1), SECONDARY(2), NONE(0);

        private final int cmuNumber;

        StressLevel(int cmuNumber) {
            this.cmuNumber = cmuNumber;
        }

        public int cmuNumber() {
            return cmuNumber;
        }
    }

    public static StressLevel getStressLevelFromCmuNumber(int cmuNumber) {
        for (StressLevel stressLevel : StressLevel.values()) {
            if (stressLevel.cmuNumber() == cmuNumber) {
                return stressLevel;
            }
        }
        return StressLevel.NONE;
    }

    public static int getStressDifferenceScore(int syllableOne, int syllableTwo) {
        StressLevel stressLevelSyllableOne = getStressLevelFromCmuNumber(syllableOne);
        StressLevel stressLevelSyllableTwo = getStressLevelFromCmuNumber(syllableTwo);

        return getStressDifferenceScore(stressLevelSyllableOne, stressLevelSyllableTwo);
    }

    public static int getStressDifferenceScore(StressLevel syllableOne, StressLevel syllableTwo) {
        switch (syllableOne) {
        case PRIMARY:
            switch (syllableTwo) {
            case PRIMARY:
                return 0;
            case SECONDARY:
                return PRIMARY_SECONDARY_SCORE;
            case NONE:
                return PRIMARY_NONE_SCORE;
            default:
                return 0;
            }

        case SECONDARY:
            switch (syllableTwo) {
            case PRIMARY:
                return PRIMARY_EMPTY_SCORE;
            case SECONDARY:
                return 0;
            case NONE:
                return SECONDARY_NONE_SCORE;
            default:
                return 0;
            }

        case NONE:
            switch (syllableTwo) {
            case PRIMARY:
                return PRIMARY_NONE_SCORE;
            case SECONDARY:
                return SECONDARY_NONE_SCORE;
            case NONE:
                return 0;
            default:
                return 0;
            }
        default:
            return 0;
        }
    }

    public static int getStressDifferenceScore(int syllable) {
        StressLevel stressLevelSyllable = getStressLevelFromCmuNumber(syllable);

        return getStressDifferenceScore(stressLevelSyllable);
    }

    public static int getStressDifferenceScore(StressLevel syllable) {
        switch (syllable) {
        case PRIMARY:
            return PRIMARY_EMPTY_SCORE;
        case SECONDARY:
            return SECONDARY_EMPTY_SCORE;
        case NONE:
            return NONE_EMPTY_SCORE;
        default:
            return 0;
        }
    }
}