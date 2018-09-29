package com.thatvineyard.snapswriter.fitness;

import com.thatvineyard.snapswriter.format.Phrase;
import com.thatvineyard.snapswriter.metre.Metre;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

public class AnalyzedPhrase extends Phrase {

    private Metre metre;
    private MetreCalculator calculator;

    public AnalyzedPhrase(Phrase phrase, MetreCalculator calculator) {
        super(phrase);
        this.metre = new Metre();
        this.calculator = calculator;
        this.metre = Metre.join(calculator.calculateMetresFromWords(getContent()));
    }

    public static AnalyzedPhrase analyze(Phrase phrase, MetreCalculator calculator) {
        return new AnalyzedPhrase(phrase, calculator);
    }

    @Override
    public void append(String word) {
        super.append(word);
        metre.append(calculator.calculateMetreFromWord(word));
    }

    public int metreDifference(AnalyzedPhrase other) {
        return metre.metreDifference(other.metre);
    }

    public int getSyllables() {
        return metre.getSyllables();
    }

    public String toStringWithMetre() {
        return toString() + " [" + metre.toString() + "]";
    }


    public boolean equals(AnalyzedPhrase other) {
        return super.equals(other) && metre.equals(other.metre);
    }
}
