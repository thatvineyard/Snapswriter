package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Phrase
 */
public class Phrase {

    private Collection<String> content;

    // CONSTRUCTORS

    public Phrase() {
        this.content = new LinkedList<>();
    }

    public Phrase(Collection<String> content) {
        this.content = content;
    }

    public Phrase(Phrase phrase) {
        this.content = phrase.content;
    }

    // ACCESSORS

    public Collection<String> getContent() {
        return content;
    }

    // MUTATORS

    public void append(String word) {
        content.add(word);
    }

    // FORMATTERS

    public String toString() {
        StringBuilder result = new StringBuilder();

        if (content == null) {
            return result.toString();
        }

        Iterator<String> contentIterator = content.iterator();

        for (int i = 0; i < content.size(); i++) {
            result.append(contentIterator.next());
            if (i != content.size() - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // COMPARATORS

    public boolean equals(Phrase other) {
        return content.equals(other.content);
    }
}