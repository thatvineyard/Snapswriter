package com.thatvineyard.snapswriter.fitness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Phrase;

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
    public void getBestCandidateFromEmptyList() {

        Collection<Candidate> candidateList = new LinkedList<Candidate>();

        Candidate bestCandidate = Candidate.getBestCandidate(candidateList);

        assertNull(bestCandidate);
    }

    @Test
    public void candidatesContainTheSamePhraseSamePhrase() {

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurresction craighead, bedside enforcability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        AnalyzedPhrase phrase = analyzedPassage.getPhrasesIterator().next();

        candidateA.addPhrase(phrase, 0);
        candidateB.addPhrase(phrase, 0);

        boolean expected = true;
        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertEquals(expected, actual);
    }

    @Test
    public void candidatesContainTheSamePhraseDifferentPhrase() {

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurresction craighead, bedside enforcability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Iterator<AnalyzedPhrase> phraseIterator = analyzedPassage.getPhrasesIterator();

        AnalyzedPhrase phraseA = phraseIterator.next();
        AnalyzedPhrase phraseB = phraseIterator.next();

        candidateA.addPhrase(phraseA, 0);
        candidateB.addPhrase(phraseB, 0);

        boolean expected = false;
        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertEquals(expected, actual);
    }

    @Test
    public void candidatesContainTheSamePhraseSamePhraseDifferentNumberOfPhrases() {

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurresction craighead, bedside enforcability.");
        AnalyzedPassage analyzedPassage = new AnalyzedPassage(passage, calculator);

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Iterator<AnalyzedPhrase> phraseIterator = analyzedPassage.getPhrasesIterator();

        AnalyzedPhrase phraseA = phraseIterator.next();
        AnalyzedPhrase phraseB = phraseIterator.next();

        candidateA.addPhrase(phraseA, 0);
        candidateB.addPhrase(phraseB, 0);
        candidateB.addPhrase(phraseA, 0);

        boolean expected = true;
        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertEquals(expected, actual);
    }
}
