package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.metre.analysis.MetreCalculator;
import com.thatvineyard.snapswriter.songcatalog.files.SongCatalog;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LyricFetcher {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    SongCatalog songCatalog;

    public LyricFetcher() {
    }

    public AnalyzedPassage getAnalyzedPassage(String songId) {
        MetreCalculator metreCalculator = createCalculator();

        Song song = getSong(songId);
        Passage songText = song.getPassage();

        return metreCalculator.analyzePassage(songText);
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
