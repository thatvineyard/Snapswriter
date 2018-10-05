package com.thatvineyard.snapswriter.format;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Passage
 */
public class Passage implements PassageInterface<Line> {

    private Collection<Line> lines;

    public Passage() {
        lines = new LinkedList<>();
    }

    public Passage(PassageInterface<? extends LineInterface> other) {
        this();
        for (LineInterface line : other.getLines()) {
            lines.add(new Line(line));
        }
    }

    public void add(Line line) {
        lines.add(line);
    }

    public void append(PassageInterface<Line> other) {
        lines.addAll(other.getLines());
    }

    @JsonIgnore
    public Iterator<Line> getLineIterator() {
        return lines.iterator();
    }

    public int getNumberOfLines() {
        return lines.size();
    }

    public boolean containsSamePhraseAs(PassageInterface<Line> other) {
        for (Line line : lines) {
            if (other.getLines().contains(line)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Passage other) {
        return lines.equals(other.lines);
    }

    public Collection<Line> getPhrasesWhere(Predicate<Line> phrasePredicate) {
        Collection<Line> result = new LinkedList<>();

        for (Line line : lines) {
            if (phrasePredicate.test(line)) {
                result.add(line);
            }
        }

        return result;
    }

    public Collection<Line> getLines() {
        return lines;
    }


}
