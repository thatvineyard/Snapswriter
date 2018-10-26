package com.thatvineyard.snapswriter.model.metre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * LineMetre
 */
public class LineMetre implements LineMetreInterface {

    private Collection<WordMetre> words;

    // CONSTRUCTORS

    public LineMetre() {
        words = new LinkedList<>();
    }

    public LineMetre(Collection<? extends WordMetreInterface> words) {
        this();
        for (WordMetreInterface word : words) {
            add(new WordMetre(word));
        }
    }

    public LineMetre(LineMetreInterface other) {
        this(other.getMetreList());
    }

    // MUTATORS

    public void add(WordMetreInterface word) {
        if (word instanceof WordMetre) {
            words.add((WordMetre) word);
        } else {
            words.add(new WordMetre(word));
        }
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
            if (wordMetre != null) {
                syllables += wordMetre.getSyllables();
            }
        }
        return syllables;
    }

    @JsonIgnore
    public Collection<? extends WordMetreInterface> getMetreList() {
        return words;
    }

    public int metreDifference(MetreInterface other) {
        return getStressSequence().stressDifference(other.getStressSequence());
    }

    @JsonIgnore
    public StressSequence getStressSequence() {
        StressSequence result = new StressSequence();
        for (WordMetre wordMetre :
                words) {
            result.append(wordMetre.getStressSequence());
        }
        return result;
    }

    @JsonIgnore
    public Iterator<WordMetre> getMetreIterator() {
        return words.iterator();
    }

    // COMPARATORS

    public int compareSyllables(LineMetre other) {
        return Integer.compare(getSyllables(), other.getSyllables());
    }

    public boolean equals(LineMetre other) {
        return compareSyllables(other) == 0 && metreDifference(other) == 0;
    }

    // FORMATTERS

    @JsonProperty("stress-sequence")
    public String toString() {
        String result = "";
        for (WordMetre wordMetre : words) {
            result += wordMetre.toString();
        }
        return result;
    }

}
