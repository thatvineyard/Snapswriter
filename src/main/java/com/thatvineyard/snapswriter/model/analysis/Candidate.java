package com.thatvineyard.snapswriter.model.analysis;

import com.thatvineyard.snapswriter.model.metre.calculator.MetreCalculator;

import java.util.Collection;

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

    public static Candidate getPlaceholderCandidate(int syllables) {
        Candidate placeholderCandidate = new Candidate();
        placeholderCandidate.addPhrase(MetreCalculator.getPlaceholderAnalyzedLine(syllables), 0);
        return placeholderCandidate;
    }

    public void addPhrase(AnalyzedLine phrase, int metreDifference) {
        passage.add(phrase);
        score += metreDifference;
    }

    public void append(Candidate other) {
        if(other != null) {
            passage.append(other.passage);
            score += other.score;
        }
    }

    public boolean isBetterThan(Candidate other) {
        if (other != null) {
            return score < other.score;
        } else {
            return true;
        }
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
