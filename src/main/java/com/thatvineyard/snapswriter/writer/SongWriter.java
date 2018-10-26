package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.model.Snapssong;
import com.thatvineyard.snapswriter.model.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.model.analysis.FitnessCalculator;
import com.thatvineyard.snapswriter.model.format.Passage;
import com.thatvineyard.snapswriter.model.format.Song;
import com.thatvineyard.snapswriter.model.metre.calculator.MetreCalculator;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class SongWriter {

    private static final Logger log = Logger.getLogger(SongWriter.class);

    @Inject
    LyricFetcher lyricFetcher;
    @Inject
    MetreCalculator metreCalculator;

    FitnessCalculator fitnessCalculator;

    public SongWriter() {
    }

    @PostConstruct
    public void init() {
        fitnessCalculator = new FitnessCalculator();
    }

    public Song matchMelodyAndTopic(AnalyzedPassage melody, AnalyzedPassage topic) {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        return fitnessCalculator.matchTopicWithMelody(topic, melody);
    }

    public Snapssong writeSnapssong(String melodySongId, String topicSongId) {
        AnalyzedPassage melody = lyricFetcher.getAnalyzedPassage(melodySongId);
        AnalyzedPassage topic = lyricFetcher.getAnalyzedPassage(topicSongId);

        Song song = fitnessCalculator.matchTopicWithMelody(topic, melody);
        int score = fitnessCalculator.getScore();

        return new Snapssong(melodySongId, topicSongId, score, song);
    }

    public Snapssong writeSnapssong(String melodySongId, Passage topic) {
        AnalyzedPassage melody = lyricFetcher.getAnalyzedPassage(melodySongId);
        AnalyzedPassage analyzedTopic = metreCalculator.analyzePassage(topic);

        Song song = fitnessCalculator.matchTopicWithMelody(analyzedTopic, melody);
        int score = fitnessCalculator.getScore();

        return new Snapssong(melodySongId, "", score, song);
    }
}
