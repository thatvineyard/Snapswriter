package com.thatvineyard.snapswriter.model.metre;

public interface MetreInterface {

    int getSyllables();
    StressSequence getStressSequence();
    int metreDifference(MetreInterface other);
}
