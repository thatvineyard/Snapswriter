package com.thatvineyard.snapswriter.format;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thatvineyard.snapswriter.writer.Snapssong;

/**
 * Formatter
 */
public class Formatter {

    private static final String PHRASE_DELIMITER_REGEX = "[.;\n?!,,]";
    private static final String WORD_DELIMITER_REGEX = "[^\\p{L}']";

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

    // SONG

    public static Song stringToSong(String text) {
        return new Song("", stringToPassage(text));
    }

    public static Song jsonToSong(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Song.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // PASSAGE

    public static Passage stringToPassage(String text) {
        Passage result = new Passage();

        String[] phraseStrings = splitStringAndRemoveEmpty(text, PHRASE_DELIMITER_REGEX);
        for (String phraseString : phraseStrings) {
            result.add(stringToLine(phraseString));
        }

        return result;
    }

    public String passageToString(PassageInterface<? extends LineInterface> passage) {
        Collection<? extends LineInterface> phrases = passage.getLines();

        StringBuilder result = new StringBuilder();
        boolean firstPhrase = true;
        for (LineInterface line : phrases) {
            if (!firstPhrase) {
                result.append(passageInfix);
            }
            result.append(phraseToString(line));
            firstPhrase = false;
        }

        return result.toString();
    }

    // PHRASE

    public String phraseToString(LineInterface line) {
        String result = line.toString();
        result = capitalizeFirstLetterIfSettingIsTrue(result);
        result = addPrefixAndSuffix(result);
        return result;
    }

    public static Line stringToLine(String text) {
        String[] words = text.split(WORD_DELIMITER_REGEX);
        words = removeEmptyStrings(words);

        Line line = new Line();
        for (String word : words) {
            line.add(new Word(word));
        }
        return line;
    }

    // SONG

    public String songToString(Snapssong snapssong) {
        String result = "Score: " + snapssong.getScore() + "\n";
        result += "SongId: " + snapssong.getMelodySongId() + "\n";
        result += "TextId: " + snapssong.getTopicSongId() + "\n";
        result += "Lyrics: " + "\n";
        result += "=============" + "\n";
        result += passageToString(snapssong.getSong().getPassage()) + "\n";
        result += "=============";
        return result;
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

    // TODO: DRY (stringToLine)
    private static String[] splitStringAndRemoveEmpty(String text, String delimiter) {
        String[] strings = text.split(delimiter);
        strings = removeEmptyStrings(strings);

        return strings;
    }

    private static String[] removeEmptyStrings(String[] strings) {
        LinkedList<String> stringList = new LinkedList<>(Arrays.asList(strings));

        Predicate<String> emptyStringPredicate = s -> s.equals("");
        stringList.removeIf(emptyStringPredicate);

        return stringList.toArray(new String[0]);
    }

}
