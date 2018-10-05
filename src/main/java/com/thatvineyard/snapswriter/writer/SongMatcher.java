package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;

public class SongMatcher {

    public static AnalyzedPassage matchMelodyAndTopic(AnalyzedPassage melody,
                                                      AnalyzedPassage topic) {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        return fitnessCalculator.matchTopicWithMelody(topic, melody);
    }

}
