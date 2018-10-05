package com.thatvineyard.snapswriter.fitness;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Line;
import com.thatvineyard.snapswriter.format.LineInterface;
import com.thatvineyard.snapswriter.format.WordInterface;
import com.thatvineyard.snapswriter.metre.LineMetre;
import com.thatvineyard.snapswriter.metre.StressSequence;
import com.thatvineyard.snapswriter.metre.WordMetre;

import java.util.Collection;
import java.util.Iterator;

public class AnalyzedLine implements LineInterface<AnalyzedWord> {

    private Collection<AnalyzedWord> words;
    private LineMetre metre;

    public AnalyzedLine(Collection<AnalyzedWord> words, LineMetre metre) {
        this.words = words;
        this.metre = metre;
    }

    public Collection<AnalyzedWord> getWords() {
        return words;
    }

    public void add(AnalyzedWord word) {
        words.add(word);
    }

    public void append(LineInterface<AnalyzedWord> other) {
        words.addAll(other.getWords());
    }

    public LineMetre getMetre() {
        return metre;
    }

    public Iterator<AnalyzedWord> getWordIterator() {
        return words.iterator();
    }

    public int metreDifference(AnalyzedLine other) {
        return metre.metreDifference(other.getMetre());
    }

    public int getSyllables() {
        return metre.getSyllables();
    }

    public String toString() {
        return toStringWithMetre();
    }

    public String toStringWithMetre() {
        return super.toString() + " [" + metre.toString() + "]";
    }

    public boolean equals(AnalyzedLine other) {
        return super.equals(other) && metre.equals(other.metre);
    }

    // SERIALIZER

    @JsonProperty("content")
    public String wordSerializer() {
        return toString();
    }

    @JsonProperty("metre")
    public String metreSereializer() {
        return metre.toString();
    }

    @JsonCreator
    public Line deserializer(String words) {
        return Formatter.stringToLine(words);
    }
}
