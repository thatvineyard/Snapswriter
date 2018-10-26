package com.thatvineyard.snapswriter;

import com.thatvineyard.snapswriter.model.format.Formatter;

public class App {
    private static final String resourceDir = "src/main/resources/";
    private static final String testDictionaryFilePath = resourceDir + "cmudict-0.7b.txt";
    private static final String allStarPath = resourceDir + "all-star.txt";
    private static final String communismPath = resourceDir + "communism-wiki.txt";

    private static Formatter createFormatter() {
        return new Formatter();
    }

    public static void main(String[] args) {

//        Formatter formatter = createFormatter();
//        FitnessCalculator calculator = new FitnessCalculator();
//
//        String song = FileImporter.readFile(allStarPath);
//        String text = FileImporter.readFile(communismPath);
//
//        Passage songPassage = formatter.stringToPassage(song);
//        Passage textPassage = formatter.stringToPassage(text);
//
//        Passage newSongTextPassage = calculator.matchTopicWithMelody(textPassage, songPassage);
//
//        System.out.println(calculator.getScore());
//        System.out.println(newSongTextPassage.toStringWithMetre());
    }
}
