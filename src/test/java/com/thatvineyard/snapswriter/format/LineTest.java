package com.thatvineyard.snapswriter.format;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * LineTest
 */
public class LineTest {


    public Collection<String> createWordList() {
        Collection<String> result = new LinkedList<>();
        result.add("Hello");
        result.add("world");

        return result;
    }

//    @Test
//    public void append_emptyList() {
//        Line line = new Line(new LinkedList<>());
//
//        line.append("Hello");
//        line.append("world");
//
//        Collection<String> expected = createWordList();
//        Collection<String> actual = line.getWords();
//
//        assertEquals(expected, actual);
//    }
}
