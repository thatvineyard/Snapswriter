package com.thatvineyard.snapswriter.metre;

import java.util.Collection;
import java.util.LinkedList;

/**
 * LineMetre
 */
public class LineMetre {

    private Collection<WordMetre> words;

    // CONSTRUCTORS

    public LineMetre() {
        words = new LinkedList<>();
    }

    public LineMetre(Collection<WordMetre> words) {
        this.words = words;
    }

    // MUTATORS

    public void add(WordMetre wordMetre) {
        words.add(wordMetre);
    }

    public void append(LineMetre other) {
        words.addAll(other.words);
    }

    public static LineMetre join(Iterable<LineMetre> metres) {
        LineMetre result = new LineMetre();

        for (LineMetre lineMetre : metres) {
            result.append(lineMetre);
        }

        return result;
    }

    // ACCESSORS

    public int getSyllables() {
        int syllables = 0;
        for (WordMetre wordMetre : words) {
            // TODO: this is just hiding another bug
            if(wordMetre != null) {
                syllables += wordMetre.getSyllables();
            }
        }
        return syllables;
    }

    public int metreDifference(LineMetre other) {
        return getFlattenedStressSequence().stressDifference(other.getFlattenedStressSequence());
    }

    private StressSequence getFlattenedStressSequence() {
        StressSequence result = new StressSequence();
        for (WordMetre wordMetre :
                words) {
            result.append(wordMetre.getStressSequence());
        }
        return result;
    }

    // COMPARATORS

    public int compareSyllables(LineMetre other) {
        return Integer.compare(getSyllables(), other.getSyllables());
    }

    public boolean equals(LineMetre other) {
        return compareSyllables(other) == 0 && metreDifference(other) == 0;
    }

    // FORMATTERS

    public String toString() {
        String result = "";
        for (WordMetre wordMetre : words) {
            result += wordMetre.toString();
        }
        return result;
    }

}
