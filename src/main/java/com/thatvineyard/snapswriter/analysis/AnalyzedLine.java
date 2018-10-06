package com.thatvineyard.snapswriter.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thatvineyard.snapswriter.format.Line;
import com.thatvineyard.snapswriter.format.LineInterface;
import com.thatvineyard.snapswriter.format.Word;
import com.thatvineyard.snapswriter.metre.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class AnalyzedLine implements LineInterface<AnalyzedWord>, LineMetreInterface {

    private Collection<AnalyzedWord> words;

    public AnalyzedLine(Line line, LineMetre metre) {
        words = new LinkedList<>();

        Iterator<Word> wordIterator = line.getWordIterator();
        Iterator<WordMetre> wordMetreIterator = metre.getMetreIterator();
        while (wordIterator.hasNext() && wordMetreIterator.hasNext()) {
            words.add(new AnalyzedWord(wordIterator.next(), wordMetreIterator.next()));
        }
    }

    public AnalyzedLine(Collection<AnalyzedWord> words) {
        this.words = words;
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
        return new LineMetre(words);
    }

    @JsonIgnore
    public Iterator<AnalyzedWord> getWordIterator() {
        return words.iterator();
    }

    @JsonIgnore
    public Collection<? extends WordMetreInterface> getMetreList() {
        return words;
    }

    public int metreDifference(AnalyzedLine other) {
        return getMetre().metreDifference(other.getMetre());
    }

    @JsonIgnore
    public int getSyllables() {
        return getMetre().getSyllables();
    }

    @JsonIgnore
    public StressSequence getStressSequence() {
        StressSequence result = new StressSequence();

        for (AnalyzedWord word : words) {
            result.append(word.getStressSequence());
        }

        return result;
    }

    public int metreDifference(MetreInterface other) {
        return getMetre().metreDifference(other);
    }

    public boolean equals(AnalyzedLine other) {
        return super.equals(other) && getMetre().equals(other.getMetre());
    }

    // SERIALIZER

}
