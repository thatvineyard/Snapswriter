package com.thatvineyard.snapswriter.metre.analysis;

import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.metre.LineMetre;

import java.util.Collection;
import java.util.LinkedList;

public class PassageMetre {

    private Collection<LineMetre> lines;

    public PassageMetre() {
        lines = new LinkedList<>();
    }

    public PassageMetre(Collection<LineMetre> lines) {
        this.lines = lines;
    }

    public void add(LineMetre line) {
        lines.add(line);
    }

}
