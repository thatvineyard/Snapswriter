package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.metre.MetreCalculator;

import org.junit.Before;
import org.junit.Test;

/**
 * PhraseTest
 */
public class PhraseTest {

    // TODO: Move to AnalyzedPhraseTest
//    private static final String testDictionaryFilePath = "testdict.txt";
//    private MetreCalculator calculator;

    // TODO: Move to AnalyzedPhraseTest
//    @Before
//    public void createMetreCalculator() {
//        calculator = new MetreCalculator(testDictionaryFilePath);
//    }

    // TODO: Move to AnalyzedPhraseTest
//    @Test
//    public void compareMetreDifferenceOneWordEach() {
//
//        Collection<String> listA = new LinkedList<String>();
//        listA.add("FRIEDMANN");
//        Collection<String> listB = new LinkedList<String>();
//        listB.add("RACED");
//
//        Phrase phraseA = new Phrase(listA, calculator);
//        Phrase phraseB = new Phrase(listB, calculator);
//
//        Boolean actual = phraseA.metreDifference(phraseB) > 0;
//
//        assertTrue(actual);
//    }

    // TODO: Move to AnalyzedPhraseTest
//    @Test
//    public void compareMetreDifferenceSeveralWordsEach() {
//
//        Collection<String> listA = new LinkedList<String>();
//        listA.add("FRIEDMANN");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<String>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Phrase phraseA = new Phrase(listA, calculator);
//        Phrase phraseB = new Phrase(listB, calculator);
//
//        int expected = 0;
//        int actual = phraseA.metreDifference(phraseB);
//
//        assertEquals(expected, actual);
//    }

    // TODO: Move to AnalyzedPhraseTest
//    @Test
//    public void compareMetreDifferenceSeveralWordsEachIncludingEmptyString() {
//
//        Collection<String> listA = new LinkedList<String>();
//        listA.add("FRIEDMANN");
//        listA.add("");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<String>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Phrase phraseA = new Phrase(listA, calculator);
//        Phrase phraseB = new Phrase(listB, calculator);
//
//        int expected = 0;
//        int actual = phraseA.metreDifference(phraseB);
//
//        assertEquals(expected, actual);
//    }

    // TODO: Move to AnalyzedPhraseTest
//    @Test
//    public void compareMetreDifferenceSeveralWordsEachIncludingSymbols() {
//
//        Collection<String> listA = new LinkedList<String>();
//        listA.add("FRIEDMANN");
//        listA.add("@$#!");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<String>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Phrase phraseA = new Phrase(listA, calculator);
//        Phrase phraseB = new Phrase(listB, calculator);
//
//        int expected = 0;
//        int actual = phraseA.metreDifference(phraseB);
//
//        assertEquals(expected, actual);
//    }

    // TODO: Move to AnalyzedPhraseTest
//    @Test
//    public void compareMetreDifferenceSeveralWordsEachIncludingUnknownWords() {
//
//        Collection<String> listA = new LinkedList<String>();
//        listA.add("FRIEDMANN");
//        listA.add("Hello");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<String>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Phrase phraseA = new Phrase(listA, calculator);
//        Phrase phraseB = new Phrase(listB, calculator);
//
//        int expected = 0;
//        int actual = phraseA.metreDifference(phraseB);
//
//        assertEquals(expected, actual);
//    }

}