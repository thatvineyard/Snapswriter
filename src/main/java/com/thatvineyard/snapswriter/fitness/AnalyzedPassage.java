package com.thatvineyard.snapswriter.fitness;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thatvineyard.snapswriter.format.Song;
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

    public AnalyzedPassage(Song song, MetreCalculator calculator) {
        phrases = new LinkedList<>();
        for (Phrase phrase : song.getPhrases()) {
            AnalyzedPhrase analyzedPhrase = new AnalyzedPhrase(phrase, calculator);
            if(analyzedPhrase.getSyllables() != 0) {
                phrases.add(analyzedPhrase);
            }
        }
    }

    public void add(AnalyzedPhrase phrase) {
        phrases.add(phrase);
    }

    public void append(PassageInterface<AnalyzedPhrase> other) {
        phrases.addAll(other.getPhrases());
    }

    @JsonIgnore
    public Iterator<AnalyzedPhrase> getPhrasesIterator() {
        return phrases.iterator();
    }

    private String formatAsLinesWithMetre() {
        StringBuilder result = new StringBuilder();

        Iterator<AnalyzedPhrase> phraseIterator = getPhrasesIterator();

        while (phraseIterator.hasNext()) {
            result.append(phraseIterator.next().toStringWithMetre()).append("\n");
        }

        return result.toString();
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
        Collection<AnalyzedPhrase> result = new LinkedList<>();

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
        for (AnalyzedPhrase phrase : phrases) {
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


