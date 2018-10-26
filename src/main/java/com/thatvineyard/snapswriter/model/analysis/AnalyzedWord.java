package com.thatvineyard.snapswriter.model.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.model.format.Word;
import com.thatvineyard.snapswriter.model.format.WordInterface;
import com.thatvineyard.snapswriter.model.metre.MetreInterface;
import com.thatvineyard.snapswriter.model.metre.StressSequence;
import com.thatvineyard.snapswriter.model.metre.WordMetre;
import com.thatvineyard.snapswriter.model.metre.WordMetreInterface;

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
