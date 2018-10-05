package com.thatvineyard.snapswriter.format;

public class Word implements WordInterface{

    String content;

    public Word(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
