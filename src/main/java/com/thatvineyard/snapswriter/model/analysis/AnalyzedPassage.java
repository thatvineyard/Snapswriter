package com.thatvineyard.snapswriter.model.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.model.metre.*;
import com.thatvineyard.snapswriter.model.format.Line;
import com.thatvineyard.snapswriter.model.format.Passage;
import com.thatvineyard.snapswriter.model.format.PassageInterface;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class AnalyzedPassage implements PassageInterface<AnalyzedLine>, PassageMetreInterface {

    Collection<AnalyzedLine> lines;

    public AnalyzedPassage() {
        lines = new LinkedList<>();
    }

    public AnalyzedPassage(Passage passage, PassageMetre metre) {
        lines = new LinkedList<>();

        Iterator<Line> lineIterator = passage.getLineIterator();
        Iterator<LineMetre> lineMetreIterator = metre.getMetreIterator();
        while (lineIterator.hasNext() && lineMetreIterator.hasNext()) {
            lines.add(new AnalyzedLine(lineIterator.next(), lineMetreIterator.next()));
        }
    }

    public AnalyzedPassage(Collection<AnalyzedLine> lines) {
        this.lines = lines;
    }

//    public AnalyzedPassage(Collection<AnalyzedLine> lines) {
//        this.lines = lines;
//    }

    public void add(AnalyzedLine line) {
        lines.add(line);
    }

    public void append(PassageInterface<AnalyzedLine> other) {
        lines.addAll(other.getLines());
    }

    @JsonIgnore
    public Iterator<AnalyzedLine> getLineIterator() {
        return lines.iterator();
    }

    @JsonIgnore
    public Collection<? extends LineMetreInterface> getMetreList() {
        return lines;
    }

    public int getNumberOfLines() {
        return lines.size();
    }

    @JsonIgnore
    public int getSyllables() {
        int syllables = 0;

        for (AnalyzedLine line : lines) {
            syllables += line.getSyllables();
        }

        return syllables;
    }

    @JsonIgnore
    public StressSequence getStressSequence(){
        StressSequence result = new StressSequence();

        for (AnalyzedLine line : lines) {
            result.append(line.getStressSequence());
        }

        return result;
    }

    @Override
    public int metreDifference(MetreInterface other) {
        return getMetre().metreDifference(other);
    }

    public PassageMetre getMetre() {
        return new PassageMetre(lines);
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


