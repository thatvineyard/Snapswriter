package com.thatvineyard.snapswriter.fitness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Phrase;

import org.junit.Test;

/**
 * CandidateTest
 */
public class CandidateTest {

    @Test
    public void getBestCandidatFromEmptyList() {

        Collection<Candidate> candidateList = new LinkedList<Candidate>();

        Candidate bestCandidate = Candidate.getBestCandidate(candidateList);

        assertNull(bestCandidate);
    }

    @Test
    public void candidatesContainTheSamePhraseSamePhrase() {

        String testDictionaryFilePath = "src/test/resources/testdict.txt";

        Formatter formatter = new Formatter(testDictionaryFilePath);

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurresction craighead, bedside enforcability.");

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Phrase phrase = passage.getPhrasesIterator().next();

        candidateA.addPhrase(phrase, 0);
        candidateB.addPhrase(phrase, 0);

        boolean expected = true;
        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertEquals(expected, actual);
    }

    @Test
    public void candidatesContainTheSamePhraseDifferentPhrase() {

        String testDictionaryFilePath = "src/test/resources/testdict.txt";

        Formatter formatter = new Formatter(testDictionaryFilePath);

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurresction craighead, bedside enforcability.");

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Iterator<Phrase> phraseIterator = passage.getPhrasesIterator();

        Phrase phraseA = phraseIterator.next();
        Phrase phraseB = phraseIterator.next();

        candidateA.addPhrase(phraseA, 0);
        candidateB.addPhrase(phraseB, 0);

        boolean expected = false;
        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertEquals(expected, actual);
    }

    @Test
    public void candidatesContainTheSamePhraseSamePhraseDifferentNumberOfPhrases() {

        String testDictionaryFilePath = "src/test/resources/testdict.txt";

        Formatter formatter = new Formatter(testDictionaryFilePath);

        Passage passage = formatter.stringToPassage("Friedmann Libor. Insurresction craighead, bedside enforcability.");

        Candidate candidateA = new Candidate();
        Candidate candidateB = new Candidate();

        Iterator<Phrase> phraseIterator = passage.getPhrasesIterator();

        Phrase phraseA = phraseIterator.next();
        Phrase phraseB = phraseIterator.next();

        candidateA.addPhrase(phraseA, 0);
        candidateB.addPhrase(phraseB, 0);
        candidateB.addPhrase(phraseA, 0);

        boolean expected = true;
        boolean actual = candidateA.containsSamePhrases(candidateB);

        assertEquals(expected, actual);
    }
}
