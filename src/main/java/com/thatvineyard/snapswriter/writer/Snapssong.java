package com.thatvineyard.snapswriter.writer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.analysis.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Song;

public class Snapssong {

    private String melodySongId;
    private String topicSongId;

    private int score;

    private Song song;

    public Snapssong(String melodySongId, String topicSongId, int score, Song song) {
        this.melodySongId = melodySongId;
        this.topicSongId = topicSongId;
        this.score = score;
        this.song = song;
    }

    public Song getSong() {
        return song;
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

    public void setSong(Song song) {
        this.song = song;
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
