package com.thatvineyard.snapswriter.metre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * CmuInterfaceTest
 */
public class CmuReaderTest {

    private static final String testDictionaryFilePath = "src/test/resources/testdict.txt";

    @Test
    public void loadFullDictionaryNoExceptions() {
        try {
            CmuReader.loadDictionary();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void loadTestDictionaryNoExceptions() {
        try {
            CmuReader.loadDictionary(testDictionaryFilePath);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void loadTestDictionaryCheckSize() {
        CmuDatabase database = CmuReader.loadDictionary(testDictionaryFilePath);

        int expected = 26;
        int actual = database.size();

        assertEquals(expected, actual);

    }

}