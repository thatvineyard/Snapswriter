package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.analysis.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Song;

public class SongMatcher {

    public static Song matchMelodyAndTopic(AnalyzedPassage melody,
                                           AnalyzedPassage topic) {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        return fitnessCalculator.matchTopicWithMelody(topic, melody);
    }

}
