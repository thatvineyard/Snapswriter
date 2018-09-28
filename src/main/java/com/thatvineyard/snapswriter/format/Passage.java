package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Passage
 */
public class Passage implements PassageInterface<Phrase> {

    private Collection<Phrase> phrases;

    public Passage() {
        phrases = new LinkedList<Phrase>();
    }

    public void add(Phrase phrase) {
        phrases.add(phrase);
    }

    public void append(PassageInterface<Phrase> other) {
        phrases.addAll(other.getPhrases());
    }

    public Iterator<Phrase> getPhrasesIterator() {
        return phrases.iterator();
    }

    public int getNumberOfPhrases() {
        return phrases.size();
    }

    public boolean containsSamePhraseAs(PassageInterface<Phrase> other) {
        for (Phrase phrase : phrases) {
            if (other.getPhrases().contains(phrase)) {
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

    public Collection<Phrase> getPhrases() {
        return phrases;
    }


}