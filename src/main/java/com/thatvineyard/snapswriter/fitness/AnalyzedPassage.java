package com.thatvineyard.snapswriter.fitness;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.format.*;
import com.thatvineyard.snapswriter.metre.LineMetre;
import com.thatvineyard.snapswriter.metre.analysis.MetreCalculator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class AnalyzedPassage implements PassageInterface<AnalyzedLine> {

    Collection<AnalyzedLine> lines;

    public AnalyzedPassage() {
        lines = new LinkedList<>();
    }

    public AnalyzedPassage(Collection<AnalyzedLine> lines) {
        this.lines = lines;
    }

    public void add(AnalyzedLine line) {
        lines.add(line);
    }

    public void append(PassageInterface<AnalyzedLine> other) {
    lines.addAll(other.getLines());
    }

    public Iterator<AnalyzedLine> getLineIterator() {
        return lines.iterator();
    }

    public int getNumberOfLines() {
        return lines.size();
    }

    private String formatAsLinesWithMetre() {
        StringBuilder result = new StringBuilder();

        Iterator<AnalyzedLine> lineIterator = getLineIterator();

        while (lineIterator.hasNext()) {
            result.append(lineIterator.next().toStringWithMetre()).append("\n");
        }

        return result.toString();
    }

    public String toStringWithMetre() {
        return formatAsLinesWithMetre();
    }

    public int getSyllables() {
        int syllables = 0;

        for (AnalyzedLine line : lines) {
            syllables += line.getSyllables();
        }

        return syllables;
    }


    public AnalyzedLine getLineAfterSyllable(int syllables) {
        int syllableCount = 0;

        for (AnalyzedLine line : lines) {

            if (syllableCount >= syllables) {
                return line;
            }

            syllableCount += line.getSyllables();
        }

        return null;

    }

    public AnalyzedLine getPhraseContainingSyllable(int syllables) {
        int syllableCount = 0;

        if (syllables == 0) {
            return null;
        }

        for (AnalyzedLine phrase : lines) {

            syllableCount += phrase.getSyllables();

            if (syllableCount >= syllables) {
                return phrase;
            }

        }

        return null;
    }


    public Collection<AnalyzedLine> getPhrasesWhere(Predicate<AnalyzedLine> phrasePredicate) {
        Collection<AnalyzedLine> result = new LinkedList<>();

        for (AnalyzedLine phrase : lines) {
            if (phrasePredicate.test(phrase)) {
                result.add(phrase);
            }
        }

        return result;
    }

    public boolean containsSamePhraseAs(PassageInterface<AnalyzedLine> other) {
        for (AnalyzedLine phrase : lines) {
            if (other.getLines().contains(phrase)) {
                return true;
            }
        }
        return false;
    }

    public Collection<AnalyzedLine> getLines() {
        return lines;
    }
}


