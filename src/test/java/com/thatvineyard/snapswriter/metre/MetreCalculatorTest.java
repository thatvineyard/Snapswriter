package com.thatvineyard.snapswriter.metre;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * MetreCalculatorTest
 */
public class MetreCalculatorTest {

    private static final String testDictionaryFilePath = "testdict.txt";
    private MetreCalculator calculator;

    @Before
    public void createMetreCalculator() {
        calculator = new MetreCalculator(testDictionaryFilePath);
    }

    @Test
    public void calculateMetreFromTestDictionaryWordExists() {
        Metre metre = calculator.calculateMetreFromWord("FRIEDMANN");

        String expected = "10";
        String actual = metre.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void calculateMetreFromTestDictionaryListOfWords() {
        Collection<String> wordList = new LinkedList<>();

        wordList.add("FRIEDMANN");
        wordList.add("DOBBERSTEIN");

        Collection<Metre> metres = calculator.calculateMetresFromWords(wordList);

        Metre metre = Metre.join(metres);

        String expected = "10102";
        String actual = metre.toString();

        assertEquals(expected, actual);
    }
}