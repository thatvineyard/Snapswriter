package com.thatvineyard.snapswriter.metre;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * CmuEntryTest
 */
public class CmuEntryTest {

    @Test
    public void pronounciationToStressSequenceOnlyVowels() {

        CmuEntry entry = new CmuEntry("word", "AH1 AH2 AH0");

        String expected = "120";
        String actual = entry.getStressSequence().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void pronounciationToStressSequenceNoVowels() {

        CmuEntry entry = new CmuEntry("word", "P CK F");

        String expected = "";
        String actual = entry.getStressSequence().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void pronounciationToStressSequence() {

        CmuEntry entry = new CmuEntry("word", "AH1 CK AH2 T AH0 R");

        String expected = "120";
        String actual = entry.getStressSequence().toString();

        assertEquals(expected, actual);
    }
}