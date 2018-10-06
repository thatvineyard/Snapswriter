package com.thatvineyard.snapswriter.metre;

public interface MetreInterface {

    int getSyllables();
    StressSequence getStressSequence();
    int metreDifference(MetreInterface other);
}
