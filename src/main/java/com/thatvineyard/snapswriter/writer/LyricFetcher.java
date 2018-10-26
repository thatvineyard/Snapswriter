package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.model.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.persistence.catalog.metrecatalog.MetreCatalog;
import com.thatvineyard.snapswriter.persistence.catalog.passagecatalog.PassageCatalog;
import com.thatvineyard.snapswriter.persistence.catalog.songcatalog.SongCatalog;
import com.thatvineyard.snapswriter.model.format.Passage;
import com.thatvineyard.snapswriter.model.format.Song;
import com.thatvineyard.snapswriter.model.metre.PassageMetre;
import com.thatvineyard.snapswriter.model.metre.calculator.MetreCalculator;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LyricFetcher {

    private static final Logger log = Logger.getLogger(LyricFetcher.class);

    @Inject
    PassageCatalog passageCatalog;
    @Inject
    MetreCatalog metreCatalog;
    @Inject
    SongCatalog songCatalog;
    @Inject
    MetreCalculator metreCalculator;

    public LyricFetcher() {
    }

    public AnalyzedPassage getAnalyzedPassage(String passageId) {
        Passage passage = passageCatalog.getPassage(passageId);
        PassageMetre passageMetre = metreCatalog.getPassageMetre(passageId);

        if (passageMetre == null) {
            passageMetre = metreCalculator.calculateMetreFromPassage(passage);
            metreCatalog.putPassageMetreInCache(passageId, passageMetre);
        }

        AnalyzedPassage result = new AnalyzedPassage(passage, passageMetre);

        log.debug(result);

        return result;
    }

    public Song getSong(String songId) {
        Song song = songCatalog.getSong(songId);
        if (song == null) {
            Passage passage = passageCatalog.getPassage(songId);
            song = new Song(songId, passage);
        }

        log.debug(song);

        return song;
    }

    public Passage getPassage(String passageId) {
        Passage passage = passageCatalog.getPassage(passageId);

        log.debug(passage);

        return passage;
    }

    public void putPassage(String passageId, Passage passage) {
        passageCatalog.putPassageInCache(passageId, passage);
    }
}
