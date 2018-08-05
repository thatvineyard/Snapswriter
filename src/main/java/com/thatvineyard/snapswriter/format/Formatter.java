package com.thatvineyard.snapswriter.format;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Predicate;

import com.thatvineyard.snapswriter.metre.MetreCalculator;

/**
 * Formatter
 */
public class Formatter {

    private final Character[] PHRASEDELIMITERARRAY = { '.', ';', '\n', '?', '!', ',' };
    private final String WORDDELIMITERREGEX = "[^a-zA-Z']";

    private final String dictionaryFilePath = "/cmudict-0.7b.txt";

    private MetreCalculator calculator;

    public Formatter() {
        calculator = new MetreCalculator(dictionaryFilePath);
    }

    public Formatter(String dictionaryFilePath) {
        calculator = new MetreCalculator(dictionaryFilePath);
    }

    public Passage textToPassage(String text) {

        String[] phraseStrings = splitStringIntoPhraseStrings(text);

        Passage result = new Passage();

        for (String phraseString : phraseStrings) {
            result.add(formatStringToPhrase(phraseString));
        }

        return result;
    }

    private String[] splitStringIntoPhraseStrings(String text) {
        String delimiterRegex = "[";
        for (Character character : PHRASEDELIMITERARRAY) {
            delimiterRegex += character;
        }
        delimiterRegex += "]";

        String[] phraseStrings = text.split(delimiterRegex);
        phraseStrings = removeEmptyStrings(phraseStrings);

        return phraseStrings;
    }

    private Phrase formatStringToPhrase(String text) {
        String[] words = text.split(WORDDELIMITERREGEX);
        words = removeEmptyStrings(words);

        return new Phrase(Arrays.asList(words), calculator);
    }

    private String[] removeEmptyStrings(String[] strings) {
        LinkedList<String> stringList = new LinkedList<String>(Arrays.asList(strings));

        Predicate<String> emptyStringPredicate = s -> s.equals("");
        stringList.removeIf(emptyStringPredicate);

        return stringList.toArray(new String[stringList.size()]);
    }

}