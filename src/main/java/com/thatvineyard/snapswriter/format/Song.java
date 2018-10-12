package com.thatvineyard.snapswriter.format;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class Song {

    private String title;
    private Passage passage;

    // TODO: Temporary, the passage concept should not be visible upwards
    public Song(String title, Passage passage) {
        this.title = title;
        this.passage = passage;
    }

    @JsonCreator
    public Song(@JsonProperty("title") String title, @JsonProperty("lyrics") Collection<Line> lines) {
        this.title = title;
        this.passage = new Passage(lines);
    }

    public Song(String title) {
        this.title = title;
        this.passage = new Passage();
    }

    // GETTERS

    public String getTitle() {
        return title;
    }

    @JsonProperty("lyrics")
    public Collection<Line> getLyrics() {
        return passage.getLines();
    }

    @JsonIgnore
    public Passage getPassage() {
        return passage;
    }

    // SETTERS

//    public void setTitle(String title) {
//        this.title = title;
//    }
}
