package com.thatvineyard.snapswriter.writer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.analysis.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Song;

public class Snapssong {

    LyricFetcher lyricFetcher;

    private String melodySongId;
    private String topicSongId;

    private int score;

    private Song lyrics;

    public Snapssong(LyricFetcher lyricFetcher, String melodySongId, String topicSongId) {
        this.lyricFetcher = lyricFetcher;
        this.melodySongId = melodySongId;
        this.topicSongId = topicSongId;
    }

    public static Snapssong writeSnapssong(LyricFetcher lyricFetcher, String melodySongId, String topicSongId) {
        Snapssong snapssong = new Snapssong(lyricFetcher, melodySongId, topicSongId);
        snapssong.writeSong();
        return snapssong;
    }

    @JsonIgnore
    public AnalyzedPassage getMelody() {
        return lyricFetcher.getAnalyzedPassage(melodySongId);
    }

    @JsonIgnore
    public AnalyzedPassage getTopic() {
        return lyricFetcher.getAnalyzedPassage(topicSongId);
    }

    public void writeSong() {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        this.lyrics = fitnessCalculator.matchTopicWithMelody(getTopic(), getMelody());
        this.score = fitnessCalculator.getScore();
    }

    public Song getLyrics() {
        return lyrics;
    }

    public int getScore() {
        return score;
    }

    public String getMelodySongId() {
        return melodySongId;
    }

    public String getTopicSongId() {
        return topicSongId;
    }

    public void setLyrics(Song lyrics) {
        this.lyrics = lyrics;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMelodySongId(String melodySongId) {
        this.melodySongId = melodySongId;
    }

    public void setTopicSongId(String topicSongId) {
        this.topicSongId = topicSongId;
    }
}
