package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.metre.Metre;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

/**
 * Phrase
 */
public class Phrase {

    private Collection<String> content;
    private Metre metre;
    private MetreCalculator calculator;

    public Phrase(MetreCalculator calculator) {
        this.content = new LinkedList<String>();
        this.metre = new Metre();
        this.calculator = calculator;
    }

    public Phrase(Collection<String> content, MetreCalculator calculator) {
        this.content = content;
        this.calculator = calculator;

        this.metre = Metre.join(calculator.calculateMetresFromWords(content));
    }

    public void append(String word) {
        content.add(word);
        metre.append(calculator.calculateMetreFromWord(word));
    }

    public int metreDifference(Phrase other) {
        return metre.metreDifference(other.metre);
    }

    public int getSyllables() {
        return metre.getSyllables();
    }

    public String toString() {
        String result = "";

        if (content == null) {
            return result;
        }

        Iterator<String> contentIterator = content.iterator();

        for (int i = 0; i < content.size(); i++) {
            result += contentIterator.next();
            if (i != content.size() - 1) {
                result += " ";
            }
        }

        return capitalizeFirstLetter(result);
    }

    public String toStringWithMetre() {
        return toString() + " [" + metre.toString() + "]";
    }

    private String capitalizeFirstLetter(String word) {
        String firstChar = word.substring(0, 1);
        firstChar = firstChar.toUpperCase();

        return firstChar + word.substring(1);
    }

    public boolean equals(Phrase other) {
        return content.equals(other.content);
    }
}