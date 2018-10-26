package com.thatvineyard.snapswriter.model.metre;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

/**
 * StressSequenceTest
 */
public class StressSequenceTest {

    @Test
    public void stressSequenceLength() {
        StressSequence sequence = new StressSequence("11");

        int expected = 2;
        int actual = sequence.getSyllables();

        assertEquals(expected, actual);
    }

    @Test
    public void stressSequenceDifferenceSameSequence() {
        StressSequence sequence = new StressSequence("11");

        int expected = 0;
        int actual = sequence.stressDifference(sequence);

        assertEquals(expected, actual);
    }

    @Test
    public void stressSequenceDifferenceSameLength() {
        StressSequence sequenceOne = new StressSequence("11");
        StressSequence sequenceTwo = new StressSequence("10");

        int stressDifference = 0;
        stressDifference += StressSequenceSettings.getStressDifferenceScore(1, 0);

        int expected = stressDifference;
        int actual = sequenceOne.stressDifference(sequenceTwo);

        assertEquals(expected, actual);
    }

    @Test
    public void stressSequenceDifferenceDifferentLength() {
        StressSequence sequenceOne = new StressSequence("111");
        StressSequence sequenceTwo = new StressSequence("10");

        int stressDifference = 0;
        stressDifference += StressSequenceSettings.getStressDifferenceScore(1, 0);
        stressDifference += StressSequenceSettings.getStressDifferenceScore(1);

        int expected = stressDifference;
        int actual = sequenceOne.stressDifference(sequenceTwo);

        assertEquals(expected, actual);
    }

    @Test
    public void concatStressSequence() {
        StressSequence sequenceOne = new StressSequence("111");
        StressSequence sequenceTwo = new StressSequence("000");

        sequenceOne.append(sequenceTwo);

        String expected = "111000";
        String actual = sequenceOne.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void joinStressSequences() {
        StressSequence sequenceOne = new StressSequence("111");
        StressSequence sequenceTwo = new StressSequence("000");

        Collection<StressSequence> stressSequenceList = new LinkedList<>();
        stressSequenceList.add(sequenceOne);
        stressSequenceList.add(sequenceTwo);

        StressSequence combinedSequence = StressSequence.join(stressSequenceList);

        String expected = "111000";
        String actual = combinedSequence.toString();

        assertEquals(expected, actual);
    }
}