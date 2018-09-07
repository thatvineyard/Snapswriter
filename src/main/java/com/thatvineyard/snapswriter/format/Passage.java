package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Passage
 */
public class Passage {

    private Collection<Phrase> phrases;

    public Passage() {
        phrases = new LinkedList<Phrase>();
    }

    public void add(Phrase phrase) {
        phrases.add(phrase);
    }

    public void append(Passage other) {
        phrases.addAll(other.phrases);
    }

    public Iterator<Phrase> getPhrasesIterator() {
        return phrases.iterator();
    }

    public String toString() {
        return formatAsLines();
    }

    private String formatAsLines() {
        String result = "";

        Iterator<Phrase> phraseIterator = phrases.iterator();

        while (phraseIterator.hasNext()) {
            result += phraseIterator.next().toString() + "\n";
        }

        return result;
    }

    private String formatAsLinesWithMetre() {
        String result = "";

        Iterator<Phrase> phraseIterator = phrases.iterator();

        while (phraseIterator.hasNext()) {
            result += phraseIterator.next().toStringWithMetre() + "\n";
        }

        return result;
    }

    private String formatAsSentences() {
        String result = "";

        Iterator<Phrase> phraseIterator = phrases.iterator();

        while (phraseIterator.hasNext()) {
            result += phraseIterator.next().toString() + ".";
            if (phraseIterator.hasNext()) {
                result += " ";
            }
        }

        return result;
    }

    public String toStringWithSyllableCount() {
        return toString();
    }

    public String toStringWithStressSequence() {
        return toString();
    }

    public String toStringWithMetre() {
        return formatAsLinesWithMetre();
    }

    public int getNumberOfPhrases() {
        return phrases.size();
    }

    public boolean containsSamePhraseAs(Passage other) {
        for (Phrase phrase : phrases) {
            if (other.phrases.contains(phrase)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Passage other) {
        return phrases.equals(other.phrases);
    }

    public Collection<Phrase> getPhrasesWhere(Predicate<Phrase> phrasePredicate) {
        Collection<Phrase> result = new LinkedList<Phrase>();

        for (Phrase phrase : phrases) {
            if (phrasePredicate.test(phrase)) {
                result.add(phrase);
            }
        }

        return result;
    }

    public int getSyllables() {
        int syllables = 0;

        for (Phrase phrase : phrases) {
            syllables += phrase.getSyllables();
        }

        return syllables;
    }

    public Collection<Phrase> getPhrases() {
        return phrases;
    }

    public Phrase getPhraseAfterSyllable(int syllables) {
        int syllableCount = 0;

        for (Phrase phrase : phrases) {

            if (syllableCount >= syllables) {
                return phrase;
            }

            syllableCount += phrase.getSyllables();
        }

        return null;

    }

    public Phrase getPhraseContainingSyllable(int syllables) {
        int syllableCount = 0;

        if (syllables == 0) {
            return null;
        }

        for (Phrase phrase : phrases) {

            syllableCount += phrase.getSyllables();

            if (syllableCount >= syllables) {
                return phrase;
            }

        }

        return null;
    }

}