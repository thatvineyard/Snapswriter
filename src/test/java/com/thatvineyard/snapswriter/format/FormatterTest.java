package com.thatvineyard.snapswriter.format;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * FormatterTest
 */
public class FormatterTest {

    private static final String testDictionaryFilePath = "src/test/resources/testdict.txt";
    private Formatter formatter;

    @Before
    public void createFormatter() {
        formatter = new Formatter(testDictionaryFilePath);
    }

    @Test
    public void textToPassageCheckSize() {

        String text = "Hello my friend. It is nice to see you";

        Passage passage = formatter.textToPassage(text);

        int expected = 2;
        int actual = passage.getNumberOfPhrases();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageCheckContent() {

        String text = "Hello my friend. It is nice to see you.";

        Passage passage = formatter.textToPassage(text);

        String expected = "Hello my friend. It is nice to see you.";
        String actual = passage.toString();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingSemicolonsCheckContent() {

        String text = "Hello my friend; It is nice to see you.";

        Passage passage = formatter.textToPassage(text);

        String expected = "Hello my friend. It is nice to see you.";
        String actual = passage.toString();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingApostrophesCheckContent() {

        String text = "Hello my friend; It's nice to see you.";

        Passage passage = formatter.textToPassage(text);

        String expected = "Hello my friend. It's nice to see you.";
        String actual = passage.toString();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingCommasCheckContent() {

        String text = "Hello my friend, It's nice to see you.";

        Passage passage = formatter.textToPassage(text);

        String expected = "Hello my friend. It's nice to see you.";
        String actual = passage.toString();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingMiscPunctuationCheckContent() {

        String text = "Hello my/ friend; It#s nice]] to see you.";

        Passage passage = formatter.textToPassage(text);

        String expected = "Hello my friend. It s nice to see you.";
        String actual = passage.toString();

        assertEquals(expected, actual);

    }

    @Test
    public void textToPassageIncludingEmptySentenceCheckContent() {

        String text = "Hello my friend;. It is nice to see you.";

        Passage passage = formatter.textToPassage(text);

        String expected = "Hello my friend. It is nice to see you.";
        String actual = passage.toString();

        assertEquals(expected, actual);

    }

}