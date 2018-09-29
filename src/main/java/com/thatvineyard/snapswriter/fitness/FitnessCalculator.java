package com.thatvineyard.snapswriter.fitness;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * FitnessCalculator
 */
public class FitnessCalculator {

    private Candidate masterCandidate;

    private static final int SEARCH_DEPTH = 2;

    public FitnessCalculator() {
        this.masterCandidate = new Candidate();
    }

    public AnalyzedPassage matchTextWithSong(AnalyzedPassage text, AnalyzedPassage song) {
        Candidate bestCandidate;

        while (masterCandidate.getSyllables() < song.getSyllables()) {
            bestCandidate = getBestCandidateForNextSongPhrase(text, song, null, 0);
            masterCandidate.append(bestCandidate);
        }

        return masterCandidate.getPassage();
    }

    public int getScore() {
        return masterCandidate.getScore();
    }

    // TODO: Rename this once it's clearer what it does
    private Candidate getBestCandidateForNextSongPhrase(AnalyzedPassage text, AnalyzedPassage song, Candidate parentCandidate,
            int depth) {
        int syllableCount = masterCandidate.getSyllables();
        if (parentCandidate != null) {
            syllableCount += parentCandidate.getSyllables();
        }

       AnalyzedPhrase songPhrase = song.getPhraseAfterSyllable(syllableCount);

        Collection<Candidate> candidates = generateListOfCandidates(songPhrase, text);
        Collection<Candidate> filteredCandidates = filterUnfittingCandidatesUnlessAllAreUnfitting(candidates);

        // TODO: Handle cases where no candidates was found. Possibly increase/decrease
        // the syllable count?

        if (filteredCandidates.size() == 1) {
            return filteredCandidates.iterator().next();
        }

        if (depth < SEARCH_DEPTH) {
            // TODO: If depth is not reached, recurse, then merge
        }

        return pickBestCandidateFromCollection(filteredCandidates);
    }

    private Collection<Candidate> generateListOfCandidates(AnalyzedPhrase songPhrase, AnalyzedPassage textPassage) {
        int requiredSyllables = songPhrase.getSyllables();
        Predicate<AnalyzedPhrase> numberOfSyllablesEqualsRequiredSyllables = p -> p.getSyllables() == requiredSyllables;

        Collection<AnalyzedPhrase> candidatePhrases = textPassage.getPhrasesWhere(numberOfSyllablesEqualsRequiredSyllables);

        Collection<Candidate> candidates = new LinkedList<>();

        Candidate newCandidate;
        for (AnalyzedPhrase candidatePhrase : candidatePhrases) {
            int metreDifference = songPhrase.metreDifference(candidatePhrase);

            newCandidate = new Candidate();
            newCandidate.addPhrase(candidatePhrase, metreDifference);

            candidates.add(newCandidate);
        }

        return candidates;
    }

    private Collection<Candidate> filterUnfittingCandidatesUnlessAllAreUnfitting(Collection<Candidate> candidates) {
        if (candidates.size() == 1) {
            return candidates;
        }

        Collection<Candidate> filteredCandidates = new LinkedList<>();

        for (Candidate candidate : candidates) {
            if (!candidate.containsSamePhrases(masterCandidate)) {
                filteredCandidates.add(candidate);
            }
        }

        if (filteredCandidates.size() == 0) {
            return candidates;
        }

        return filteredCandidates;
    }

    private Candidate pickBestCandidateFromCollection(Collection<Candidate> candidates) {
        return Candidate.getBestCandidate(candidates);
    }

}