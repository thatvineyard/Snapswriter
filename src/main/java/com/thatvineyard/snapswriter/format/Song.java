package com.thatvineyard.snapswriter.format;

public class Song {

    private String title;
    private Passage passage;

    // TODO: Temporary, the passage concept should not be visible upwards
    public Song(String title, Passage passage) {
        this.title = title;
        this.passage = passage;
    }

    public Song(String title) {
        this.title = title;
        this.passage = new Passage();
    }

    // GETTERS

    public String getTitle() {
        return title;
    }

    public Passage getPassage() {
        return passage;
    }

    // SETTERS

//    public void setTitle(String title) {
//        this.title = title;
//    }
}
