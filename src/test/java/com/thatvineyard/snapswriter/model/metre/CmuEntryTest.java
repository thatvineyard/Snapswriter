package com.thatvineyard.snapswriter.model.metre;

import static org.junit.Assert.assertEquals;

import com.thatvineyard.snapswriter.model.metre.calculator.CmuEntry;
import org.junit.Test;

/**
 * CmuEntryTest
 */
public class CmuEntryTest {

    @Test
    public void pronunciationToStressSequenceOnlyVowels() {

        CmuEntry entry = new CmuEntry("word", "AH1 AH2 AH0");

        String expected = "120";
        String actual = entry.getStressSequence().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void pronunciationToStressSequenceNoVowels() {

        CmuEntry entry = new CmuEntry("word", "P CK F");

        String expected = "";
        String actual = entry.getStressSequence().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void pronunciationToStressSequence() {

        CmuEntry entry = new CmuEntry("word", "AH1 CK AH2 T AH0 R");

        String expected = "120";
        String actual = entry.getStressSequence().toString();

        assertEquals(expected, actual);
    }
}
