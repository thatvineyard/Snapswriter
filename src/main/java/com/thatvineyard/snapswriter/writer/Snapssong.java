package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;

import javax.ejb.EJB;
import javax.inject.Inject;

public class Snapssong {

    LyricFetcher lyricFetcher;

    private String melodySongId;
    private String topicSongId;

    private int score;

    private AnalyzedPassage lyrics;

    public Snapssong(LyricFetcher lyricFetcher, String melodySongId, String topicSongId) {
        this.lyricFetcher = lyricFetcher;
        this.melodySongId = melodySongId;
        this.topicSongId = topicSongId;
        System.out.println(lyricFetcher);
    }

    public static Snapssong writeSnapssong(LyricFetcher lyricFetcher, String melodySongId, String topicSongId) {
        Snapssong snapssong = new Snapssong(lyricFetcher, melodySongId, topicSongId);
        System.out.println("----" + snapssong.lyricFetcher);
        snapssong.writeSong();
        return snapssong;
    }

    public AnalyzedPassage getMelody() {
        System.out.println(lyricFetcher);
        return lyricFetcher.getAnalyzedPassage(melodySongId);
    }

    public AnalyzedPassage getTopic() {
        System.out.println(lyricFetcher);
        return lyricFetcher.getAnalyzedPassage(topicSongId);
    }

    public void writeSong() {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        this.lyrics = fitnessCalculator.matchTopicWithMelody(getTopic(), getMelody());
        this.score = fitnessCalculator.getScore();
    }

    public AnalyzedPassage getLyrics() {
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

    public void setLyrics(AnalyzedPassage lyrics) {
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
