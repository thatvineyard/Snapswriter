package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.function.Predicate;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.AnalyzedPhrase;
import com.thatvineyard.snapswriter.metre.MetreCalculator;
import org.junit.Before;
import org.junit.Test;

/**
 * SongTest
 */
public class SongTest {

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

    @Test
    public void getPhrasesWhereSyllablesEqual() {

        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        Predicate<AnalyzedPhrase> phrasePredicate = p -> p.getSyllables() == 4;

        Collection<AnalyzedPhrase> phrases = analyzedPassage.getPhrasesWhere(phrasePredicate);

        int expected = 1;
        int actual = phrases.size();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhrasesWhereTrue() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        Predicate<AnalyzedPhrase> phrasePredicate = p -> true;

        Collection<AnalyzedPhrase> phrases = analyzedPassage.getPhrasesWhere(phrasePredicate);

        int expected = 3;
        int actual = phrases.size();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhraseWithinSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseContainingSyllable(2);

        String expected = "Friedmann Libor";
        String actual = formatter.phraseToString(phrase);

        assertTrue(actual.contains(expected));
    }

    @Test
    public void getPhraseWithinFirstSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseContainingSyllable(0);

        assertNull(phrase);
    }

    @Test
    public void getPhraseContainingLastSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseContainingSyllable(18);

        String expected = "Bedside enforceability";
        String actual = formatter.phraseToString(phrase);

        assertTrue(actual.contains(expected));
    }

    @Test
    public void getPhraseWithinOutOfBoundsSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseContainingSyllable(19);

        assertNull(phrase);
    }

    @Test
    public void getPhraseAfterSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseAfterSyllable(2);

        String expected = "Insurrection craighead";
        String actual = formatter.phraseToString(phrase);

        assertTrue(actual.contains(expected));
    }

    @Test
    public void getPhraseAfterFirstSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseAfterSyllable(0);

        String expected = "Friedmann Libor";
        String actual = formatter.phraseToString(phrase);

        assertTrue(actual.contains(expected));
    }

    @Test
    public void getPhraseAfterLastSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseAfterSyllable(18);

        assertNull(phrase);
    }

    @Test
    public void getPhraseAfterOutOfBoundsSyllable() {
        Song song = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(song, calculator);

        AnalyzedPhrase phrase = analyzedPassage.getPhraseAfterSyllable(19);

        assertNull(phrase);
    }
}
