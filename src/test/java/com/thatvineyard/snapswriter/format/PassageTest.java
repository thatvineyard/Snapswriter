package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

/**
 * PassageTest
 */
public class PassageTest {

    private static final String testDictionaryFilePath = "src/test/resources/testdict.txt";
    private Formatter formatter;

    @Before
    public void createFormatter() {
        formatter = new Formatter(testDictionaryFilePath);
    }

    @Test
    public void getPhrasesWhereSyllablesEqual() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead bedside enforceability.");

        Predicate<Phrase> phrasePredicate = p -> p.getSyllables() == 4;

        Collection<Phrase> phrases = passage.getPhrasesWhere(phrasePredicate);

        for (Phrase phrase : phrases) {
            System.out.println(phrase + ": " + phrase.getSyllables());
        }

        int expected = 1;
        int actual = phrases.size();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhrasesWhereTrue() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforcability.");

        Predicate<Phrase> phrasePredicate = p -> true;

        Collection<Phrase> phrases = passage.getPhrasesWhere(phrasePredicate);

        int expected = 3;
        int actual = phrases.size();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhraseWithinSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforcability.");

        Phrase phrase = passage.getPhraseWithinSyllable(2);

        String expected = "Friedmann Libor";
        String actual = phrase.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhraseWithinFirstSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseWithinSyllable(0);

        assertNull(phrase);
    }

    @Test
    public void getPhraseWithinLastSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseWithinSyllable(18);

        String expected = "bedside enforceability";
        String actual = phrase.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhraseWithinOutOfBoundsSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseWithinSyllable(19);

        assertNull(phrase);
    }

    @Test
    public void getPhraseAfterSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseAfterSyllable(2);

        String expected = "Insurrection craighead";
        String actual = phrase.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhraseAfterFirstSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseAfterSyllable(0);

        String expected = "Friedmann Libor";
        String actual = phrase.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getPhraseAfterLastSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseAfterSyllable(18);

        assertNull(phrase);
    }

    @Test
    public void getPhraseAfterOutOfBoundsSyllable() {
        Passage passage = formatter.textToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");

        Phrase phrase = passage.getPhraseAfterSyllable(19);

        assertNull(phrase);
    }
}