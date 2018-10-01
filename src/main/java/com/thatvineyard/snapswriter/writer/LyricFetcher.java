package com.thatvineyard.snapswriter.writer;

import com.thatvineyard.snapswriter.files.FileImporter;
import com.thatvineyard.snapswriter.files.FileMapper;
import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

public class LyricFetcher {

    public static AnalyzedPassage getAnalyzedPassage(String fileId) {
        Formatter formatter = createFormatter();
        FileMapper fileMapper = createFileMapper();
        MetreCalculator metreCalculator = createCalculator();

        String song = FileImporter.getFileText(fileMapper.getFilepath(fileId));

        Passage songPassage = formatter.stringToPassage(song);

        return new AnalyzedPassage(songPassage, metreCalculator);
    }


    private static FileMapper createFileMapper() {
        return new FileMapper();
    }

    private static Formatter createFormatter() {
        return new Formatter();
    }

    private static MetreCalculator createCalculator() {
        MetreCalculator calculator = new MetreCalculator();
        calculator.useTextgain(false);
        return calculator;
    }

}
