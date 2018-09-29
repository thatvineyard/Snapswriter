package com.thatvineyard.snapswriter.fitness;

import com.thatvineyard.snapswriter.format.Phrase;
import com.thatvineyard.snapswriter.metre.MetreCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalzyedPhraseTest {

    private static final String testDictionaryFilePath = "testdict.txt";
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

        Phrase phraseA = new Phrase(listA);
        Phrase phraseB = new Phrase(listB);

        AnalyzedPhrase analyzedPhraseA = new AnalyzedPhrase(phraseA, calculator);
        AnalyzedPhrase analyzedPhraseB = new AnalyzedPhrase(phraseB, calculator);

        Boolean actual = analyzedPhraseA.metreDifference(analyzedPhraseB) > 0;

        assertTrue(actual);
    }

    @Test
    public void compareMetreDifferenceSeveralWordsEach() {

        Collection<String> listA = new LinkedList<String>();
        listA.add("FRIEDMANN");
        listA.add("HOLTZCLAW");
        Collection<String> listB = new LinkedList<String>();
        listB.add("RACED");
        listB.add("TECUMSEH");

        Phrase phraseA = new Phrase(listA);
        Phrase phraseB = new Phrase(listB);

        AnalyzedPhrase analyzedPhraseA = new AnalyzedPhrase(phraseA, calculator);
        AnalyzedPhrase analyzedPhraseB = new AnalyzedPhrase(phraseB, calculator);

        int expected = 0;
        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);

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

        Phrase phraseA = new Phrase(listA);
        Phrase phraseB = new Phrase(listB);

        AnalyzedPhrase analyzedPhraseA = new AnalyzedPhrase(phraseA, calculator);
        AnalyzedPhrase analyzedPhraseB = new AnalyzedPhrase(phraseB, calculator);

        int expected = 0;
        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);

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

        Phrase phraseA = new Phrase(listA);
        Phrase phraseB = new Phrase(listB);

        AnalyzedPhrase analyzedPhraseA = new AnalyzedPhrase(phraseA, calculator);
        AnalyzedPhrase analyzedPhraseB = new AnalyzedPhrase(phraseB, calculator);

        int expected = 0;
        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);

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

        Phrase phraseA = new Phrase(listA);
        Phrase phraseB = new Phrase(listB);

        AnalyzedPhrase analyzedPhraseA = new AnalyzedPhrase(phraseA, calculator);
        AnalyzedPhrase analyzedPhraseB = new AnalyzedPhrase(phraseB, calculator);

        int expected = 0;
        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);

        assertEquals(expected, actual);
    }

}
