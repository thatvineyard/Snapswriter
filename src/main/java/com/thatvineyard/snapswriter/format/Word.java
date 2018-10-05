package com.thatvineyard.snapswriter.format;

import com.fasterxml.jackson.annotation.JsonValue;

public class Word implements WordInterface{

    String content;

    public Word(String content) {
        this.content = content;
    }

    public Word(WordInterface other) {
        this(other.getContent());
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return content;
    }
}
