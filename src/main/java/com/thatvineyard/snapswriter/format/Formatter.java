package com.thatvineyard.snapswriter.format;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

import com.thatvineyard.snapswriter.fitness.AnalyzedPhrase;

/**
 * Formatter
 */
public class Formatter {

    private static final String PHRASE_DELIMITER_REGEX = "[.;\n?!,,]";
    private static final String WORD_DELIMITER_REGEX = "[^a-zA-Z']";

    private String phrasePrefix = "";
    private String phraseSuffix = ".";
    private String passageInfix = "\n";
    private Boolean capitalizeFirstLetter = true;

    public Formatter() {

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

    public String passageToString(PassageInterface<? extends Phrase> passage) {
        Collection<? extends Phrase> phrases = passage.getPhrases();

        StringBuilder result = new StringBuilder();
        boolean firstPhrase = true;
        for (Phrase phrase : phrases) {
            if (!firstPhrase) {
                result.append(passageInfix);
            }
            result.append(phraseToString(phrase));
            firstPhrase = false;
        }

        return result.toString();
    }

    public String analyzedPassageToString(PassageInterface<AnalyzedPhrase> passage) {
        Collection<AnalyzedPhrase> phrases = passage.getPhrases();

        StringBuilder result = new StringBuilder();
        boolean firstPhrase = true;
        for (Phrase phrase : phrases) {
            if (!firstPhrase) {
                result.append(passageInfix);
            }
            result.append(phraseToString(phrase));
            firstPhrase = false;
        }

        return result.toString();
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

        return new Phrase(Arrays.asList(words));
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

    // TODO: DRY (stringToPhrase)
    private String[] splitStringAndRemoveEmpty(String text, String delimiter) {
        String[] strings = text.split(delimiter);
        strings = removeEmptyStrings(strings);

        return strings;
    }

    private String[] removeEmptyStrings(String[] strings) {
        LinkedList<String> stringList = new LinkedList<>(Arrays.asList(strings));

        Predicate<String> emptyStringPredicate = s -> s.equals("");
        stringList.removeIf(emptyStringPredicate);

        return stringList.toArray(new String[0]);
    }

}