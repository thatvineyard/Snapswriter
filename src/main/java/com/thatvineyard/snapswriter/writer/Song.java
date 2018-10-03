package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

import java.util.Collection;

public class Song {

    private String songId;
    private String textId;

    private int score;

    private AnalyzedPassage lyrics;

    public Song(String songId, String textId) {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        this.songId = songId;
        this.textId = textId;

        AnalyzedPassage songPassage = LyricFetcher.getAnalyzedPassage(songId);
        AnalyzedPassage textPassage = LyricFetcher.getAnalyzedPassage(textId);

        this.lyrics = fitnessCalculator.matchTextWithSong(textPassage, songPassage);

        this.score = fitnessCalculator.getScore();
    }

    public AnalyzedPassage getLyrics() {
        return lyrics;
    }

    public int getScore() {
        return score;
    }

    public String getSongId() {
        return songId;
    }

    public String getTextId() {
        return textId;
    }

    public void setLyrics(AnalyzedPassage lyrics) {
        this.lyrics = lyrics;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }
}
