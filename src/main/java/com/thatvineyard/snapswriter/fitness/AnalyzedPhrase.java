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

    // TODO: Code smell. Get rid of this
    private AnalyzedPhrase(Phrase phrase, Metre metre) {
        super(phrase);
        this.metre = metre;
        this.calculator = null;
    }

    // TODO: Code smell. Get rid of this
    public static AnalyzedPhrase getPlaceholderAnalyzedPhrase(int syllables) {
        Phrase phrase = new Phrase();
        phrase.append("X");

        Metre metre = new Metre(syllables);

        return new AnalyzedPhrase(phrase, metre);
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

    public String toString() {
        return toStringWithMetre();
    }

    public String toStringWithMetre() {
        return super.toString() + " [" + metre.toString() + "]";
    }

    public Metre getMetre() {
        return metre;
    }

    public boolean equals(AnalyzedPhrase other) {
        return super.equals(other) && metre.equals(other.metre);
    }
}
