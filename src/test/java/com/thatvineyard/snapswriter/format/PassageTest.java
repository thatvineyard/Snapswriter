package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.thatvineyard.snapswriter.metre.calculator.MetreCalculator;
import org.junit.Before;

/**
 * PassageTest
 */
public class PassageTest {

    private static final String testDictionaryFilePath = "testdict.txt";
    private Formatter formatter;
    private MetreCalculator calculator;

    @Before
    public void setUp() {
        createFormatter();
        createMetreCalculator();
    }

    public void createFormatter() {
        formatter = new Formatter();
        formatter.setPhrasePrefix("");
        formatter.setPhraseSuffix("");
        formatter.setPassageInfix("");
    }

    public void createMetreCalculator() {
        calculator = new MetreCalculator(testDictionaryFilePath);
    }

//    @Test
//    public void getPhrasesWhereSyllablesEqual() {
//
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        Predicate<AnalyzedLine> phrasePredicate = p -> p.getSyllables() == 4;
//
//        Collection<AnalyzedLine> phrases = analyzedPassage.getPhrasesWhere(phrasePredicate);
//
//        int expected = 1;
//        int actual = phrases.size();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void getPhrasesWhereTrue() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        Predicate<AnalyzedLine> phrasePredicate = p -> true;
//
//        Collection<AnalyzedLine> phrases = analyzedPassage.getPhrasesWhere(phrasePredicate);
//
//        int expected = 3;
//        int actual = phrases.size();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void getPhraseWithinSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getPhraseContainingSyllable(2);
//
//        String expected = "Friedmann Libor";
//        String actual = formatter.phraseToString(phrase);
//
//        assertTrue(actual.contains(expected));
//    }
//
//    @Test
//    public void getPhraseWithinFirstSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getPhraseContainingSyllable(0);
//
//        assertNull(phrase);
//    }
//
//    @Test
//    public void getPhraseContainingLastSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getPhraseContainingSyllable(18);
//
//        String expected = "Bedside enforceability";
//        String actual = formatter.phraseToString(phrase);
//
//        assertTrue(actual.contains(expected));
//    }
//
//    @Test
//    public void getPhraseWithinOutOfBoundsSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getPhraseContainingSyllable(19);
//
//        assertNull(phrase);
//    }
//
//    @Test
//    public void getPhraseAfterSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getLineAfterSyllable(2);
//
//        String expected = "Insurrection craighead";
//        String actual = formatter.phraseToString(phrase);
//
//        assertTrue(actual.contains(expected));
//    }
//
//    @Test
//    public void getPhraseAfterFirstSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getLineAfterSyllable(0);
//
//        String expected = "Friedmann Libor";
//        String actual = formatter.phraseToString(phrase);
//
//        assertTrue(actual.contains(expected));
//    }
//
//    @Test
//    public void getPhraseAfterLastSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getLineAfterSyllable(18);
//
//        assertNull(phrase);
//    }
//
//    @Test
//    public void getPhraseAfterOutOfBoundsSyllable() {
//        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
//        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);
//
//        AnalyzedLine phrase = analyzedPassage.getLineAfterSyllable(19);
//
//        assertNull(phrase);
//    }
}
