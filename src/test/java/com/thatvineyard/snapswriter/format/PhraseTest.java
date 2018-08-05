package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.metre.MetreCalculator;

import org.junit.Before;
import org.junit.Test;

/**
 * PhraseTest
 */
public class PhraseTest {

    private static final String testDictionaryFilePath = "src/test/resources/testdict.txt";
    private MetreCalculator calculator;

    @Before
    public void createMetreCalculator() {
        calculator = new MetreCalculator(testDictionaryFilePath);
    }

    @Test
    public void compareMetreDifferenceOneWordEach() {

        Collection<String> listA = new LinkedList<String>();
        listA.add("FRIEDMANN");
        Collection<String> listB = new LinkedList<String>();
        listB.add("RACED");

        Phrase phraseA = new Phrase(listA, calculator);
        Phrase phraseB = new Phrase(listB, calculator);

        int expected = 5;
        int actual = phraseA.metreDifference(phraseB);

        assertEquals(expected, actual);
    }

    @Test
    public void compareMetreDifferenceSeveralWordsEach() {

        Collection<String> listA = new LinkedList<String>();
        listA.add("FRIEDMANN");
        listA.add("HOLTZCLAW");
        Collection<String> listB = new LinkedList<String>();
        listB.add("RACED");
        listB.add("TECUMSEH");

        Phrase phraseA = new Phrase(listA, calculator);
        Phrase phraseB = new Phrase(listB, calculator);

        int expected = 0;
        int actual = phraseA.metreDifference(phraseB);

        assertEquals(expected, actual);
    }

    @Test
    public void compareMetreDifferenceSeveralWordsEachIncludingEmptyString() {

        Collection<String> listA = new LinkedList<String>();
        listA.add("FRIEDMANN");
        listA.add("");
        listA.add("HOLTZCLAW");
        Collection<String> listB = new LinkedList<String>();
        listB.add("RACED");
        listB.add("TECUMSEH");

        Phrase phraseA = new Phrase(listA, calculator);
        Phrase phraseB = new Phrase(listB, calculator);

        int expected = 0;
        int actual = phraseA.metreDifference(phraseB);

        assertEquals(expected, actual);
    }

    @Test
    public void compareMetreDifferenceSeveralWordsEachIncludingSymbols() {

        Collection<String> listA = new LinkedList<String>();
        listA.add("FRIEDMANN");
        listA.add("@$#!");
        listA.add("HOLTZCLAW");
        Collection<String> listB = new LinkedList<String>();
        listB.add("RACED");
        listB.add("TECUMSEH");

        Phrase phraseA = new Phrase(listA, calculator);
        Phrase phraseB = new Phrase(listB, calculator);

        int expected = 0;
        int actual = phraseA.metreDifference(phraseB);

        assertEquals(expected, actual);
    }

    @Test
    public void compareMetreDifferenceSeveralWordsEachIncludingUnknownWords() {

        Collection<String> listA = new LinkedList<String>();
        listA.add("FRIEDMANN");
        listA.add("Hello");
        listA.add("HOLTZCLAW");
        Collection<String> listB = new LinkedList<String>();
        listB.add("RACED");
        listB.add("TECUMSEH");

        Phrase phraseA = new Phrase(listA, calculator);
        Phrase phraseB = new Phrase(listB, calculator);

        int expected = 0;
        int actual = phraseA.metreDifference(phraseB);

        assertEquals(expected, actual);
    }

}