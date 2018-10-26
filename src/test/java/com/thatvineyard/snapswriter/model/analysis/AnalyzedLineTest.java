package com.thatvineyard.snapswriter.model.analysis;

import com.thatvineyard.snapswriter.model.metre.calculator.MetreCalculator;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyzedLineTest {

    private static final String testDictionaryFilePath = "testdict.txt";
    private MetreCalculator calculator;

    @Before
    public void createMetreCalculator() {
        calculator = new MetreCalculator(testDictionaryFilePath);
        calculator.useTextgain(false);
    }

//    @Test
//    public void compareMetreDifferenceOneWordEach() {
//
//        Collection<String> listA = new LinkedList<>();
//        listA.add("FRIEDMANN");
//        Collection<String> listB = new LinkedList<>();
//        listB.add("RACED");
//
//        Line lineA = new Line(listA);
//        Line lineB = new Line(listB);
//
//        AnalyzedLine analyzedPhraseA = new AnalyzedLine(lineA, calculator);
//        AnalyzedLine analyzedPhraseB = new AnalyzedLine(lineB, calculator);
//
//        boolean actual = analyzedPhraseA.metreDifference(analyzedPhraseB) > 0;
//
//        assertTrue(actual);
//    }
//
//    @Test
//    public void compareMetreDifferenceSeveralWordsEach() {
//
//        Collection<String> listA = new LinkedList<>();
//        listA.add("FRIEDMANN");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Line lineA = new Line(listA);
//        Line lineB = new Line(listB);
//
//        AnalyzedLine analyzedPhraseA = new AnalyzedLine(lineA, calculator);
//        AnalyzedLine analyzedPhraseB = new AnalyzedLine(lineB, calculator);
//
//        int expected = 0;
//        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void compareMetreDifferenceSeveralWordsEachIncludingEmptyString() {
//
//        Collection<String> listA = new LinkedList<>();
//        listA.add("FRIEDMANN");
//        listA.add("");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Line lineA = new Line(listA);
//        Line lineB = new Line(listB);
//
//        AnalyzedLine analyzedPhraseA = new AnalyzedLine(lineA, calculator);
//        AnalyzedLine analyzedPhraseB = new AnalyzedLine(lineB, calculator);
//
//        int expected = 0;
//        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void compareMetreDifferenceSeveralWordsEachIncludingSymbols() {
//
//        Collection<String> listA = new LinkedList<>();
//        listA.add("FRIEDMANN");
//        listA.add("@$#!");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Line lineA = new Line(listA);
//        Line lineB = new Line(listB);
//
//        AnalyzedLine analyzedPhraseA = new AnalyzedLine(lineA, calculator);
//        AnalyzedLine analyzedPhraseB = new AnalyzedLine(lineB, calculator);
//
//        int expected = 0;
//        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void compareMetreDifferenceSeveralWordsEachIncludingUnknownWords() {
//
//        Collection<String> listA = new LinkedList<>();
//        listA.add("FRIEDMANN");
//        listA.add("Hello");
//        listA.add("HOLTZCLAW");
//        Collection<String> listB = new LinkedList<>();
//        listB.add("RACED");
//        listB.add("TECUMSEH");
//
//        Line lineA = new Line(listA);
//        Line lineB = new Line(listB);
//
//        AnalyzedLine analyzedPhraseA = new AnalyzedLine(lineA, calculator);
//        AnalyzedLine analyzedPhraseB = new AnalyzedLine(lineB, calculator);
//
//        int expected = 0;
//        int actual = analyzedPhraseA.metreDifference(analyzedPhraseB);
//
//        assertEquals(expected, actual);
//    }

}
