package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.metre.MetreCalculator;
import com.thatvineyard.snapswriter.songcatalog.files.SongCatalog;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LyricFetcher {

    @Inject
    SongCatalog songCatalog;

    public LyricFetcher() {
    }

    public AnalyzedPassage getAnalyzedPassage(String songId) {
        MetreCalculator metreCalculator = createCalculator();

        Song song = getSong(songId);

        return new AnalyzedPassage(song, metreCalculator);
    }

    public Song getSong(String songId) {
        return songCatalog.getSong(songId);
    }

    private static MetreCalculator createCalculator() {
        MetreCalculator calculator = new MetreCalculator();
        calculator.useTextgain(false);
        return calculator;
    }

}
