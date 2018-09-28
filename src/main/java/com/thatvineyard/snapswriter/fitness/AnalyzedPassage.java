package com.thatvineyard.snapswriter.fitness;

import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.PassageInterface;
import com.thatvineyard.snapswriter.format.Phrase;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class AnalyzedPassage implements PassageInterface<AnalyzedPhrase> {

    private Collection<AnalyzedPhrase> phrases;

    public AnalyzedPassage() {
        phrases = new LinkedList<>();
    }

    public AnalyzedPassage(Passage passage, MetreCalculator calculator) {
        phrases = new LinkedList<>();
        for (Phrase phrase : passage.getPhrases()) {
            phrases.add(new AnalyzedPhrase(phrase, calculator));
        }
    }

    public void add(AnalyzedPhrase phrase) {
        phrases.add(phrase);
    }

    public void append(PassageInterface<AnalyzedPhrase> other) {
        phrases.addAll(other.getPhrases());
    }

    public Iterator<AnalyzedPhrase> getPhrasesIterator() {
        return phrases.iterator();
    }

    private String formatAsLinesWithMetre() {
        String result = "";

        Iterator<AnalyzedPhrase> phraseIterator = getPhrasesIterator();

        while (phraseIterator.hasNext()) {
            result += phraseIterator.next().toStringWithMetre() + "\n";
        }

        return result;
    }

    public String toStringWithMetre() {
        return formatAsLinesWithMetre();
    }

    public int getSyllables() {
        int syllables = 0;

        for (AnalyzedPhrase phrase : phrases) {
            syllables += phrase.getSyllables();
        }

        return syllables;
    }


    public AnalyzedPhrase getPhraseAfterSyllable(int syllables) {
        int syllableCount = 0;

        for (AnalyzedPhrase phrase : phrases) {

            if (syllableCount >= syllables) {
                return phrase;
            }

            syllableCount += phrase.getSyllables();
        }

        return null;

    }

    public AnalyzedPhrase getPhraseContainingSyllable(int syllables) {
        int syllableCount = 0;

        if (syllables == 0) {
            return null;
        }

        for (AnalyzedPhrase phrase : phrases) {

            syllableCount += phrase.getSyllables();

            if (syllableCount >= syllables) {
                return phrase;
            }

        }

        return null;
    }


    public Collection<AnalyzedPhrase> getPhrasesWhere(Predicate<AnalyzedPhrase> phrasePredicate) {
        Collection<AnalyzedPhrase> result = new LinkedList<AnalyzedPhrase>();

        for (AnalyzedPhrase phrase : phrases) {
            if (phrasePredicate.test(phrase)) {
                result.add(phrase);
            }
        }

        return result;
    }

    public int getNumberOfPhrases() {
        return phrases.size();
    }

    public boolean containsSamePhraseAs(PassageInterface<AnalyzedPhrase> other) {
        for (Phrase phrase : phrases) {
            if (other.getPhrases().contains(phrase)) {
                return true;
            }
        }
        return false;
    }

    public Collection<AnalyzedPhrase> getPhrases() {
        return phrases;
    }
}


