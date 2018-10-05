package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * FormatterTest
 */
public class FormatterTest {

    private Formatter formatter;

    @Before
    public void createFormatter() {
        formatter = new Formatter();
        formatter.setPhraseSuffix(".");
        formatter.setPassageInfix(" ");
    }

    @Test
    public void textToPassageCheckSize() {

        String text = "Hello my friend. It is nice to see you";

        Song song = formatter.stringToPassage(text);

        int expected = 2;
        int actual = song.getNumberOfPhrases();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageCheckContent() {

        String text = "Hello my friend. It is nice to see you.";

        Song song = formatter.stringToPassage(text);

        String expected = "Hello my friend. It is nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingSemicolonsCheckContent() {

        String text = "Hello my friend; It is nice to see you.";

        Song song = formatter.stringToPassage(text);

        String expected = "Hello my friend. It is nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingApostrophesCheckContent() {

        String text = "Hello my friend; It's nice to see you.";

        Song song = formatter.stringToPassage(text);

        String expected = "Hello my friend. It's nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingCommasCheckContent() {

        String text = "Hello my friend, It's nice to see you.";

        Song song = formatter.stringToPassage(text);

        String expected = "Hello my friend. It's nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingMiscPunctuationCheckContent() {

        String text = "Hello my/ friend; It#s nice]] to see you.";

        Song song = formatter.stringToPassage(text);

        String expected = "Hello my friend. It s nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingEmptySentenceCheckContent() {

        String text = "Hello my friend;. It is nice to see you.";

        Song song = formatter.stringToPassage(text);

        String expected = "Hello my friend. It is nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);
    }

    @Test
    public void passageToStringPhrasePrefix() {

        String text = "Hello my friend. It is nice to see you.";

        Song song = formatter.stringToPassage(text);
        formatter.setPhrasePrefix("!");

        String expected = "!Hello my friend. !It is nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);
    }

    @Test
    public void passageToStringPhraseSuffix() {

        String text = "Hello my friend. It is nice to see you.";

        Song song = formatter.stringToPassage(text);
        formatter.setPhraseSuffix("!");

        String expected = "Hello my friend! It is nice to see you!";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);
    }

    @Test
    public void passageToStringPassageInfix() {

        String text = "Hello my friend. It is nice to see you.";

        Song song = formatter.stringToPassage(text);
        formatter.setPassageInfix("!");

        String expected = "Hello my friend.!It is nice to see you.";
        String actual = formatter.passageToString(song);

        assertEquals(expected, actual);
    }
}
