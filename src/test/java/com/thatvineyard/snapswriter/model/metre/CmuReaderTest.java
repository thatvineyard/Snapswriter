package com.thatvineyard.snapswriter.model.metre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.thatvineyard.snapswriter.model.metre.calculator.CmuDatabase;
import com.thatvineyard.snapswriter.model.metre.calculator.CmuReader;
import org.junit.Test;

/**
 * CmuInterfaceTest
 */
public class CmuReaderTest {

    private static final String testDictionaryFilePath = "testdict.txt";

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
            CmuReader.loadDictionaryFromFile(testDictionaryFilePath);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void loadTestDictionaryCheckSize() {
        CmuDatabase database = CmuReader.loadDictionaryFromFile(testDictionaryFilePath);

        int expected = 26;
        int actual = database.size();

        assertEquals(expected, actual);

    }

}
