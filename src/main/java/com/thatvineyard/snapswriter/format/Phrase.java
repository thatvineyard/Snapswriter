package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.metre.Metre;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

/**
 * Phrase
 */
public class Phrase {

    private Collection<String> content;

    public Phrase() {
        this.content = new LinkedList<String>();
    }

    public Phrase(Collection<String> content) {
        this.content = content;
    }

    public Phrase(Phrase phrase) {
        this.content = phrase.content;
    }

    // TODO: Remove this once calculator is decoupled
    public Collection<String> getContent() {
        return content;
    }

    public void append(String word) {
        content.add(word);
    }

    public String toString() {
        String result = "";

        if (content == null) {
            return result;
        }

        Iterator<String> contentIterator = content.iterator();

        for (int i = 0; i < content.size(); i++) {
            result += contentIterator.next();
            if (i != content.size() - 1) {
                result += " ";
            }
        }

        return result;
    }

    public boolean equals(Phrase other) {
        return content.equals(other.content);
    }
}