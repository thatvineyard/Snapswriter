package com.thatvineyard.snapswriter.metre;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

/**
 * MetreTest
 */
public class MetreTest {

    @Test
    public void metresEqualsSameMetre() {
        Metre metreOne = new Metre("11");
        Metre metreTwo = new Metre("11");

        boolean expected = true;
        boolean actual = metreOne.equals(metreTwo);

        assertEquals(expected, actual);
    }

    @Test
    public void metresEqualsDifferentMetreSameLength() {
        Metre metreOne = new Metre("11");
        Metre metreTwo = new Metre("00");

        boolean expected = false;
        boolean actual = metreOne.equals(metreTwo);

        assertEquals(expected, actual);
    }

    @Test
    public void metresEqualsSimilarMetreDifferentLength() {
        Metre metreOne = new Metre("11");
        Metre metreTwo = new Metre("111");

        boolean expected = false;
        boolean actual = metreOne.equals(metreTwo);

        assertEquals(expected, actual);
    }

    @Test
    public void AppendMetresCheckString() {
        Metre metreOne = new Metre("11");
        Metre metreTwo = new Metre("00");

        metreOne.append(metreTwo);

        String expected = "1100";
        String actual = metreOne.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void JoinMetresCheckString() {
        Collection<Metre> metres = new LinkedList<Metre>();

        metres.add(new Metre("11"));
        metres.add(new Metre("00"));

        Metre metre = Metre.join(metres);

        String expected = "1100";
        String actual = metre.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void AppendMetresCheckSyllables() {
        Metre metreOne = new Metre("11");
        Metre metreTwo = new Metre("00");

        metreOne.append(metreTwo);

        int expected = 4;
        int actual = metreOne.getSyllables();

        assertEquals(expected, actual);
    }

    @Test
    public void JoinMetresCheckSyllables() {
        Collection<Metre> metres = new LinkedList<Metre>();

        metres.add(new Metre("11"));
        metres.add(new Metre("00"));

        Metre metre = Metre.join(metres);

        int expected = 4;
        int actual = metre.getSyllables();

        assertEquals(expected, actual);
    }
}