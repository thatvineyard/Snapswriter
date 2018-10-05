package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.metre.MetreCalculator;
import com.thatvineyard.snapswriter.songcatalog.files.SongCatalog;

public class LyricFetcher {

    public static AnalyzedPassage getAnalyzedPassage(String songId) {
        MetreCalculator metreCalculator = createCalculator();

        Song song = getSong(songId);

        return new AnalyzedPassage(song, metreCalculator);
    }

    public static Song getSong(String songId) {
        return SongCatalog.getSong(songId);
    }

    private static MetreCalculator createCalculator() {
        MetreCalculator calculator = new MetreCalculator();
        calculator.useTextgain(false);
        return calculator;
    }

}
