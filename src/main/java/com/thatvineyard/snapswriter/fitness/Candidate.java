package com.thatvineyard.snapswriter.fitness;

import java.util.Collection;

import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.format.Phrase;

/**
 * Candidate
 */
public class Candidate {

    private AnalyzedPassage passage;
    private int score;

    public Candidate() {
        passage = new AnalyzedPassage();
        score = 0;
    }

    public void addPhrase(AnalyzedPhrase phrase, int metreDifference) {
        passage.add(phrase);
        score += metreDifference;
    }

    public void append(Candidate other) {
        passage.append(other.passage);
        score += other.score;
    }

    public boolean isBetterThan(Candidate other) {
        return score < other.score;
    }

    public boolean containsSamePhrases(Candidate other) {
        return passage.containsSamePhraseAs(other.getPassage());

    }

    public static Candidate getBestCandidate(Collection<Candidate> candidateList) {
        Candidate bestCandidate = null;

        for (Candidate candidate : candidateList) {
            if (bestCandidate == null || candidate.isBetterThan(bestCandidate)) {
                bestCandidate = candidate;
            }
        }

        return bestCandidate;
    }

    public AnalyzedPassage getPassage() {
        return passage;
    }

    public int getSyllables() {
        return passage.getSyllables();
    }

    public int getScore() {
        return score;
    }
}