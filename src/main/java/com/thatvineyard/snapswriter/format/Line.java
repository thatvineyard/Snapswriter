package com.thatvineyard.snapswriter.format;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Line
 */
public class Line implements LineInterface<Word> {

    private Collection<Word> words;

    // CONSTRUCTORS

    public Line() {
        this.words = new LinkedList<>();
    }

    public Line(Collection<Word> words) {
        this();
        for (Word word :
                words) {
            add(word);
        }

    }

    public Line(Line line) {
        this.words = line.words;
    }

    public Line(LineInterface<WordInterface> other) {
        this();
        for (WordInterface word : other.getWords()) {
            words.add(new Word(word));
        }
    }

    @JsonCreator
    private Line(String line) {
        this(Formatter.stringToLine(line));
    }

    // ACCESSORS

    public Collection<Word> getWords() {
        return words;
    }

    @JsonIgnore
    public Iterator<Word> getWordIterator() {
        return words.iterator();
    }

    // MUTATORS

    public void add(Word word) {
        words.add(word);
    }

//
//    public void add(String word) {
//        words.add(new Word(word));
//    }

    public void append(LineInterface<Word> other) {
        for (Word word : other.getWords()) {
            add(word);
        }
    }

    // FORMATTERS

    public String toString() {
        StringBuilder result = new StringBuilder();

        if (words == null) {
            return result.toString();
        }

        Iterator<Word> contentIterator = words.iterator();

        for (int i = 0; i < words.size(); i++) {
            result.append(contentIterator.next().toString());
            if (i != words.size() - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // COMPARATORS

    public boolean equals(Line other) {
        return words.equals(other.words);
    }


    // SERIALIZER

    @JsonValue
    public String serializer() {
        return toString();
    }

    @JsonCreator
    public Line deserializer(String words) {
        return Formatter.stringToLine(words);
    }
}
