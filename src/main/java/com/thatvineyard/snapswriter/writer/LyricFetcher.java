package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.catalog.metrecatalog.MetreCatalog;
import com.thatvineyard.snapswriter.catalog.passagecatalog.PassageCatalog;
import com.thatvineyard.snapswriter.catalog.songcatalog.SongCatalog;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.metre.PassageMetre;
import com.thatvineyard.snapswriter.metre.calculator.MetreCalculator;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.FileHandler;

@Stateless
public class LyricFetcher {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    PassageCatalog passageCatalog;
    @Inject
    MetreCatalog metreCatalog;
    @Inject
    SongCatalog songCatalog;

    public LyricFetcher() {
    }

    public AnalyzedPassage getAnalyzedPassage(String passageId) {
        MetreCalculator metreCalculator = createCalculator();

        Passage passage = passageCatalog.getPassage(passageId);
        PassageMetre passageMetre = metreCatalog.getPassageMetre(passageId);

        if(passageMetre == null) {
            passageMetre = metreCalculator.calculateMetreFromPassage(passage);
            metreCatalog.putPassageMetreInCache(passageId, passageMetre);
        }

        return new AnalyzedPassage(passage, passageMetre);
    }

    public Song getSong(String songId) {
       Song song = songCatalog.getSong(songId);
       if (song == null) {
           Passage passage = passageCatalog.getPassage(songId);
           song = new Song(songId, passage);
       }

       return song;
    }

    private static MetreCalculator createCalculator() {
        MetreCalculator calculator = new MetreCalculator();
//        MetreCalculator calculator = new MetreCalculator("cmudict-svenska-0.1.txt");
        calculator.useTextgain(false);
        return calculator;
    }

}
