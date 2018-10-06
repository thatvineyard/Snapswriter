package com.thatvineyard.snapswriter.metre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.metre.LineMetre;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class PassageMetre implements PassageMetreInterface {

    private Collection<LineMetre> lines;

    public PassageMetre() {
        lines = new LinkedList<>();
    }

    public PassageMetre(Collection<? extends LineMetreInterface> lines) {
        this();
        Iterator<? extends LineMetreInterface> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            add(new LineMetre(lineIterator.next()));
        }
    }

    public PassageMetre(PassageMetreInterface other) {
        this(other.getMetreList());
    }

    public void add(LineMetre line) {
        lines.add(line);
    }

    public int getSyllables() {
        int syllables = 0;
        for (LineMetre lineMetre : lines) {
            // TODO: this is just hiding another bug
            if (lineMetre != null) {
                syllables += lineMetre.getSyllables();
            }
        }
        return syllables;
    }

    @JsonIgnore
    public StressSequence getStressSequence() {
        StressSequence result = new StressSequence();
        for (LineMetre lineMetre :
                lines) {
            result.append(lineMetre.getStressSequence());
        }
        return result;
    }

    public int metreDifference(MetreInterface other) {
        return 0;
    }

    @JsonIgnore
    public Iterator<LineMetre> getMetreIterator() {
        return lines.iterator();
    }

    @JsonIgnore
    public Collection<? extends LineMetreInterface> getMetreList() {
        return lines;
    }

    @JsonProperty("stress-sequence")
    public String toString() {
        String result = "";
        for (LineMetre lineMetre: lines) {
            result += lineMetre.toString();
        }
        return result;
    }

}
