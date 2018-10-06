package com.thatvineyard.snapswriter.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.format.Word;
import com.thatvineyard.snapswriter.format.WordInterface;
import com.thatvineyard.snapswriter.metre.MetreInterface;
import com.thatvineyard.snapswriter.metre.StressSequence;
import com.thatvineyard.snapswriter.metre.WordMetre;
import com.thatvineyard.snapswriter.metre.WordMetreInterface;

public class AnalyzedWord implements WordInterface, WordMetreInterface {

    Word word;
    WordMetre metre;

    public AnalyzedWord(Word word, WordMetre metre) {
        this.word = word;
        this.metre = metre;
    }

    public WordMetre getMetre() {
        return metre;
    }

    public String getContent() {
        return word.getContent();
    }

    @JsonIgnore
    public int getSyllables() {
        return metre.getSyllables();
    }

    @JsonIgnore
    public StressSequence getStressSequence() {
        return metre.getStressSequence();
    }

    public int metreDifference(MetreInterface other) {
        return metre.metreDifference(other);
    }

}
