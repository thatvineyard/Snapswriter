package com.thatvineyard.snapswriter.fitness;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;

import com.thatvineyard.snapswriter.metre.MetreCalculator;
import org.junit.Before;
import org.junit.Test;

/**
 * CandidateTest
 */
public class CandidateTest {

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
    }

    public void createMetreCalculator() {
        calculator = new MetreCalculator(testDictionaryFilePath);
    }

    @Test
    public void getBestCandidate_FromEmptyList_null() {

        Collection<Candidate> candidateList = new LinkedList<>();

        Candidate bestCandidate = Candidate.getBestCandidate(candidateList);

        assertNull(bestCandidate);
    }

    @Test
    public void containsSamePhrases_SamePhrase_true() {

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        AnalyzedPhrase phrase = analyzedPassage.getPhrasesIterator().next();

        candidateA.addPhrase(phrase, 0);
        candidateB.addPhrase(phrase, 0);

        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertTrue(actual);
    }

    @Test
    public void containsSamePhrases_DifferentPhrase_false() {

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Iterator<AnalyzedPhrase> phraseIterator = analyzedPassage.getPhrasesIterator();

        AnalyzedPhrase phraseA = phraseIterator.next();
        AnalyzedPhrase phraseB = phraseIterator.next();

        candidateA.addPhrase(phraseA, 0);
        candidateB.addPhrase(phraseB, 0);

        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertFalse(actual);
    }

    @Test
    public void containsSamePhrase_SamePhraseButDifferentNumberOfPhrases_true() {

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurrection craighead, bedside enforceability");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Iterator<AnalyzedPhrase> phraseIterator = analyzedPassage.getPhrasesIterator();

        AnalyzedPhrase phraseA = phraseIterator.next();
        AnalyzedPhrase phraseB = phraseIterator.next();

        candidateA.addPhrase(phraseA, 0);
        candidateB.addPhrase(phraseB, 0);
        candidateB.addPhrase(phraseA, 0);

        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertTrue(actual);
    }
}
