package com.thatvineyard.snapswriter;

import com.thatvineyard.snapswriter.files.FileImporter;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;

public class App {
    private static final String resourceDir = "src/main/resources/";
    private static final String testDictionaryFilePath = resourceDir + "cmudict-0.7b.txt";
    private static final String allStarPath = resourceDir + "all-star.txt";
    private static final String communismPath = resourceDir + "communism-wiki.txt";

    private static Formatter createFormatter() {
        return new Formatter(testDictionaryFilePath);
    }

    public static void main(String[] args) {

        Formatter formatter = createFormatter();
        FitnessCalculator calculator = new FitnessCalculator();

        String song = FileImporter.getFileText(allStarPath);
        String text = FileImporter.getFileText(communismPath);

        Passage songPassage = formatter.textToPassage(song);
        Passage textPassage = formatter.textToPassage(text);

        Passage newSongTextPassage = calculator.matchTextWithSong(textPassage, songPassage);

        System.out.println(calculator.getScore());
        System.out.println(newSongTextPassage.toStringWithMetre());
    }
}
