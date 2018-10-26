package com.thatvineyard.snapswriter.model.metre;

import static org.junit.Assert.assertEquals;

import com.thatvineyard.snapswriter.model.metre.calculator.MetreCalculator;
import org.junit.Before;

/**
 * LineMetreCalculatorTest
 */
public class MetreCalculatorTest {

    private static final String testDictionaryFilePath = "testdict.txt";
    private MetreCalculator calculator;

    @Before
    public void createMetreCalculator() {
        calculator = new MetreCalculator(testDictionaryFilePath);
    }

//    @Test
//    public void calculateMetreFromTestDictionaryWordExists() {
//        LineMetre lineMetre = calculator.calculateMetreFromWord("FRIEDMANN");
//
//        String expected = "10";
//        String actual = lineMetre.toString();
//
//        assertEquals(expected, actual);
//    }

//    @Test
//    public void calculateMetreFromTestDictionaryListOfWords() {
//        Collection<String> wordList = new LinkedList<>();
//
//        wordList.add("FRIEDMANN");
//        wordList.add("DOBBERSTEIN");
//
//        Collection<LineMetre> lineMetres = calculator.calculateMetresFromWords(wordList);
//
//        LineMetre lineMetre = LineMetre.join(lineMetres);
//
//        String expected = "10102";
//        String actual = lineMetre.toString();
//
//        assertEquals(expected, actual);
//    }
}
