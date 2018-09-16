package com.thatvineyard.snapswriter.metre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * CmuDatabaseTest
 */
public class CmuDatabaseTest {

    private static final String testDictionaryFilePath = "testdict.txt";

    private CmuDatabase database;

    @Before
    public void setUp() {
        database = CmuReader.loadDictionary(testDictionaryFilePath);
    }

    @Test
    public void searchForExistingWordMatchingCase() {
        CmuEntry entry = database.search("FRIEDMANN");

        String expected = "FRIEDMANN";
        String actual = entry.getWord();

        assertEquals(expected, actual);
    }

    @Test
    public void searchForExistingWordNotMatchingCase() {
        CmuEntry entry = database.search("friedmann");

        String expected = "FRIEDMANN";
        String actual = entry.getWord();

        assertEquals(expected, actual);
    }

    @Test
    public void searchForEmptyString() {
        CmuEntry entry = database.search("");

        assertNull(entry);
    }
}