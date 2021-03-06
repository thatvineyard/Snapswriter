package com.thatvineyard.snapswriter.model.analysis;

import com.thatvineyard.snapswriter.model.format.Passage;
import com.thatvineyard.snapswriter.model.format.Song;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

import static com.thatvineyard.snapswriter.model.analysis.Candidate.getPlaceholderCandidate;
import static java.lang.Math.max;

/**
 * FitnessCalculator
 */
public class FitnessCalculator {

    private Logger log = Logger.getLogger(this.getClass());

    private Candidate masterCandidate;

    private static final int SEARCH_DEPTH = 1;

    public FitnessCalculator() {
        this.masterCandidate = new Candidate();
    }

    public Song matchTopicWithMelody(AnalyzedPassage text, AnalyzedPassage song) {
        Candidate bestCandidate;

        log.info("Matching topic with melody... (" + song.getSyllables() + " syllables)");
        while (masterCandidate.getSyllables() < song.getSyllables()) {
            bestCandidate = getBestCandidateForNextSongPhrase(text, song, null, 0);
            masterCandidate.append(bestCandidate);
        }
        log.info("Done! (" + song.getSyllables() + " syllables)");

        return new Song("No-Title", new Passage(masterCandidate.getPassage()));
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

        AnalyzedLine songPhrase = song.getLineAfterSyllable(syllableCount);

//        System.out.println("Syllable: " + syllableCount + ". Depth: " + depth + ". Looking for amount of syllables: " + songPhrase.getSyllables() + ".");

        Collection<Candidate> candidates = generateListOfCandidates(songPhrase, text);
        Collection<Candidate> filteredCandidates = filterUnfittingCandidatesUnlessAllAreUnfitting(candidates);

        // TODO: Handle cases where no candidates was found. Possibly increase/decrease
        // the syllable count?
        if (filteredCandidates.size() == 0) {
            return getPlaceholderCandidate(songPhrase.getSyllables());
        }

        if (filteredCandidates.size() == 1) {
            return filteredCandidates.iterator().next();
        }

        Collection<Candidate> bestCandidates = filterBestCandidates(filteredCandidates, 5);

        if (depth < SEARCH_DEPTH && syllableCount + songPhrase.getSyllables() < song.getSyllables()) {
            Candidate newCandidate;
            Candidate recurseResult;
            Candidate bestResult = null;
            for (Candidate candidate : filteredCandidates) {
                newCandidate = new Candidate();
                newCandidate.append(parentCandidate);
                newCandidate.append(candidate);

                recurseResult = getBestCandidateForNextSongPhrase(text, song, newCandidate, depth + 1);
                candidate.append(recurseResult);

                if (candidate.isBetterThan(bestResult)) {
                    bestResult = candidate;
                }
            }
            return bestResult;
        }

        return pickBestCandidateFromCollection(filteredCandidates);
    }

    private Collection<Candidate> generateListOfCandidates(AnalyzedLine songPhrase, AnalyzedPassage textPassage) {
        int requiredSyllables = songPhrase.getSyllables();
        Predicate<AnalyzedLine> numberOfSyllablesEqualsRequiredSyllables = p -> p.getSyllables() == requiredSyllables;

        Collection<AnalyzedLine> candidatePhrases = textPassage.getPhrasesWhere(numberOfSyllablesEqualsRequiredSyllables);

        Collection<Candidate> candidates = new LinkedList<>();

        Candidate newCandidate;
        for (AnalyzedLine candidatePhrase : candidatePhrases) {
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


    private Collection<Candidate> filterBestCandidates(Collection<Candidate> candidates, int numberOfCandidates) {
        if (candidates.size() <= numberOfCandidates) {
            return candidates;
        }
        Collection<Candidate> filteredCandidates = new LinkedList<>(candidates);

        Candidate worstCandidate;
        while (filteredCandidates.size() > numberOfCandidates) {
            worstCandidate = null;
            for (Candidate candidate : filteredCandidates) {
                if (!candidate.isBetterThan(worstCandidate) || worstCandidate == null) {
                    worstCandidate = candidate;
                }
            }
            filteredCandidates.remove(worstCandidate);
        }

        return filteredCandidates;
    }

    private Candidate pickBestCandidateFromCollection(Collection<Candidate> candidates) {
        return Candidate.getBestCandidate(candidates);
    }

}
