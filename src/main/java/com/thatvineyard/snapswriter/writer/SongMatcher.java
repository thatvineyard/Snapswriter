package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;

public class SongMatcher {

    public static AnalyzedPassage matchSongAndTextPassage(AnalyzedPassage song, AnalyzedPassage text) {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        return fitnessCalculator.matchTextWithSong(text, song);
    }

}
