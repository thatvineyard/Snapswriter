package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.catalog.passagecatalog.PassageCatalog;
import com.thatvineyard.snapswriter.catalog.songcatalog.SongCatalog;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.metre.calculator.MetreCalculator;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LyricFetcher {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    PassageCatalog passageCatalog;
    @Inject
    SongCatalog songCatalog;

    public LyricFetcher() {
    }

    public AnalyzedPassage getAnalyzedPassage(String passageId) {
        MetreCalculator metreCalculator = createCalculator();

        Passage songText = passageCatalog.getPassage(passageId);

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
