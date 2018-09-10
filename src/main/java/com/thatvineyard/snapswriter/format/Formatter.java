package com.thatvineyard.snapswriter.format;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

import com.thatvineyard.snapswriter.metre.MetreCalculator;

/**
 * Formatter
 */
public class Formatter {

    private final String PHRASE_DELIMITER_REGEX = "[.;\n?!,,]";
    private final String WORD_DELIMITER_REGEX = "[^a-zA-Z']";

    private final String dictionaryFilePath = "/cmudict-0.7b.txt";

    private MetreCalculator calculator;

    private String phrasePrefix = "";
    private String phraseSuffix = ".";
    private String passageInfix = "\n";
    private Boolean capitalizeFirstLetter = true;

    // TODO: break out calculator out of formatter. it's not in the formatter's
    // scope.
    public Formatter() {
        calculator = new MetreCalculator(dictionaryFilePath);
    }

    public Formatter(String dictionaryFilePath) {
        calculator = new MetreCalculator(dictionaryFilePath);
    }

    // SETTERS

    public void setPhrasePrefix(String prefix) {
        phrasePrefix = prefix;
    }

    public void setPhraseSuffix(String suffix) {
        phraseSuffix = suffix;
    }

    public void setPassageInfix(String infix) {
        passageInfix = infix;
    }

    public void setCapitalizeFirstLetter(boolean value) {
        capitalizeFirstLetter = value;
    }

    // PASSAGE

    public Passage stringToPassage(String text) {
        Passage result = new Passage();

        String[] phraseStrings = splitStringAndRemoveEmpty(text, PHRASE_DELIMITER_REGEX);
        for (String phraseString : phraseStrings) {
            result.add(stringToPhrase(phraseString));
        }

        return result;
    }

    public String passageToString(Passage passage) {
        Collection<Phrase> phrases = passage.getPhrases();

        String result = "";
        boolean firstPhrase = true;
        for (Phrase phrase : phrases) {
            if (!firstPhrase) {
                result += passageInfix;
            }
            result += phraseToString(phrase);
            firstPhrase = false;
        }

        return result;
    }

    // PHRASE

    public String phraseToString(Phrase phrase) {
        String result = phrase.toString();
        result = capitalizeFirstLetterIfSettingIsTrue(result);
        result = addPrefixAndSuffix(result);
        return result;
    }

    private Phrase stringToPhrase(String text) {
        String[] words = text.split(WORD_DELIMITER_REGEX);
        words = removeEmptyStrings(words);

        return new Phrase(Arrays.asList(words), calculator);
    }

    // FORMATTING

    private String addPrefixAndSuffix(String text) {
        return phrasePrefix + text + phraseSuffix;
    }

    private String capitalizeFirstLetterIfSettingIsTrue(String text) {
        if (capitalizeFirstLetter) {
            return capitalizeFirstLetter(text);
        } else {
            return text;
        }
    }

    private String capitalizeFirstLetter(String text) {
        String firstChar = text.substring(0, 1);
        firstChar = firstChar.toUpperCase();

        return firstChar + text.substring(1);
    }

    private String[] splitStringAndRemoveEmpty(String text, String delimiter) {
        String[] strings = text.split(delimiter);
        strings = removeEmptyStrings(strings);

        return strings;
    }

    private String[] removeEmptyStrings(String[] strings) {
        LinkedList<String> stringList = new LinkedList<String>(Arrays.asList(strings));

        Predicate<String> emptyStringPredicate = s -> s.equals("");
        stringList.removeIf(emptyStringPredicate);

        return stringList.toArray(new String[stringList.size()]);
    }

}