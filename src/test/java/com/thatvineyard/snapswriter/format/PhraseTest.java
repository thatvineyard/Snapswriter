package com.thatvineyard.snapswriter.format;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * PhraseTest
 */
public class PhraseTest {


    public Collection<String> createWordList() {
        Collection<String> result = new LinkedList<>();
        result.add("Hello");
        result.add("world");

        return result;
    }

    @Test
    public void append_emptyList() {
        Phrase phrase = new Phrase(new LinkedList<>());

        phrase.append("Hello");
        phrase.append("world");

        Collection<String> expected = createWordList();
        Collection<String> actual = phrase.getContent();

        assertEquals(expected, actual);
    }
}