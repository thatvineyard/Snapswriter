package com.thatvineyard.snapswriter.fitness;

import com.thatvineyard.snapswriter.format.WordInterface;
import com.thatvineyard.snapswriter.metre.WordMetre;

public class AnalyzedWord implements WordInterface {

    String content;
    WordMetre metre;

    public AnalyzedWord(String content, WordMetre metre) {
        this.content = content;
        this.metre = metre;
    }

    public AnalyzedWord(WordInterface word, WordMetre metre) {
        this.content = word.getContent();
    }

    public String getContent() {
        return content;
    }

    public WordMetre getMetre() {
        return metre;
    }
}
