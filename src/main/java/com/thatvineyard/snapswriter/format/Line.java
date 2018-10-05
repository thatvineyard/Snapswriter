package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Line
 */
public class Line implements LineInterface<Word> {

    private Collection<Word> content;

    // CONSTRUCTORS

    public Line() {
        this.content = new LinkedList<>();
    }

    public Line(Collection<Word> content) {
        this.content = content;
    }

    public Line(Line line) {
        this.content = line.content;
    }

    // ACCESSORS

    public Collection<Word> getWords() {
        return content;
    }

    public Iterator<Word> getWordIterator() {
        return content.iterator();
    }

    // MUTATORS

    public void add(Word word) {
        content.add(word);
    }

    public void add(String word) {
        content.add(new Word(word));
    }

    public void append(LineInterface<Word> other) {
        content.addAll(other.getWords());
    }

    // FORMATTERS

    public String toString() {
        StringBuilder result = new StringBuilder();

        if (content == null) {
            return result.toString();
        }

        Iterator<Word> contentIterator = content.iterator();

        for (int i = 0; i < content.size(); i++) {
            result.append(contentIterator.next().toString());
            if (i != content.size() - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // COMPARATORS

    public boolean equals(Line other) {
        return content.equals(other.content);
    }
}
