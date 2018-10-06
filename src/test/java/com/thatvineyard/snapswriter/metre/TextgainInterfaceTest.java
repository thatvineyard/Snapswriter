// package com.thatvineyard.snapswriter.metre;

// import static org.junit.Assert.assertEquals;

// import com.thatvineyard.snapswriter.metre.calculator.TextgainReader;

// import org.junit.Test;

// /**
// * TextgainInterfaceTest
// */
// public class TextgainInterfaceTest {

// @Test
// public void countZeroSyllables() {

// int actual = TextgainReader.numberOfSyllablesInString("");
// int expected = 0;

// assertEquals(expected, actual);
// }

// @Test
// public void countOneSyllable() {

// int actual = TextgainReader.numberOfSyllablesInString("and");
// int expected = 1;

// assertEquals(expected, actual);
// }

// @Test
// public void countTwoSyllables() {

// int actual = TextgainReader.numberOfSyllablesInString("hello");
// int expected = 2;

// assertEquals(expected, actual);
// }

// @Test
// public void countSyllablesInTwoWords() {

// int actual = TextgainReader.numberOfSyllablesInString("hello friend");
// int expected = 3;

// assertEquals(expected, actual);
// }

// @Test
// public void countOneSyllablesWithPunctuation() {

// int actual = TextgainReader.numberOfSyllablesInString("hello my friend,
// how are you doing? I am fine");
// int expected = 12;

// assertEquals(expected, actual);
// }

// }
