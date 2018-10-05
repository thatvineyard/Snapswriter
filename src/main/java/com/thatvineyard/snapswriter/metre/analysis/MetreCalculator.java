package com.thatvineyard.snapswriter.metre.analysis;

import com.thatvineyard.snapswriter.fitness.AnalyzedLine;
import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.AnalyzedWord;
import com.thatvineyard.snapswriter.format.*;
import com.thatvineyard.snapswriter.metre.LineMetre;
import com.thatvineyard.snapswriter.metre.StressSequence;
import com.thatvineyard.snapswriter.metre.WordMetre;

import java.util.Collection;
import java.util.LinkedList;

/**
 * MetreCalculator
 */
public class MetreCalculator {

    CmuDatabase database;

    private boolean useCmuDatabase = true;
    private boolean useTextgain = true;

    public MetreCalculator() {
        loadDatabase();
    }

    public MetreCalculator(String dictionaryFilePath) {
        loadDatabase(dictionaryFilePath);
    }

    // SETTINGS

    public void useCmuDatabase(boolean value) {
        useCmuDatabase = value;
    }

    public void useTextgain(boolean value) {
        useTextgain = value;
    }

    // MUTATORS

    public void loadDatabase() {
        database = CmuReader.loadDictionary();
    }

    public void loadDatabase(String dictionaryFilePath) {
        database = CmuReader.loadDictionaryFromFile(dictionaryFilePath);
    }

    // ANALYZER

    public AnalyzedWord analyzeWord(WordInterface word) {
        return new AnalyzedWord(word, calculateMetreFromWord(word));
    }

    public AnalyzedLine analyzeLine(LineInterface<WordInterface> line) {
        Collection<WordInterface> words = line.getWords();
        Collection<AnalyzedWord> analyzedWords = new LinkedList<>();
        for (WordInterface word :
                words) {
            analyzedWords.add(analyzeWord(word));
        }
        return new AnalyzedLine(analyzedWords, unzipLineMetre(analyzedWords));
    }

    public AnalyzedPassage analyzePassage(PassageInterface<? extends LineInterface> passage) {
        Collection<? extends LineInterface> lines = passage.getLines();
        Collection<AnalyzedLine> analyzedLines = new LinkedList<>();
        for (LineInterface line :
                lines) {
            analyzedLines.add(analyzeLine(line));
        }
        return new AnalyzedPassage(analyzedLines);
    }

    // PLACEHOLDERS

    public static AnalyzedWord getPlaceholderAnalyzedWord(int syllables) {
                return new AnalyzedWord("<placeholder word>", new WordMetre(new StressSequence(syllables)));
    }

    public static AnalyzedLine getPlaceholderAnalyzedLine(int syllables) {
        Collection<AnalyzedWord> words = new LinkedList<>();
        words.add(getPlaceholderAnalyzedWord(syllables));
        return new AnalyzedLine(words, unzipLineMetre(words));
    }

    public static AnalyzedPassage getPlaceholderAnalyzedPassage(int syllables) {
        Collection<AnalyzedLine> lines = new LinkedList<>();
        lines.add(getPlaceholderAnalyzedLine(syllables));
        return new AnalyzedPassage(lines);
    }

    // CALCULATORS
    private WordMetre calculateMetreFromWord(WordInterface word) {
        WordMetre wordMetre = null;

        if (useCmuDatabase) {
            CmuEntry cmuEntry = database.search(word.getContent());
            if (cmuEntry != null) {
                wordMetre = new WordMetre(cmuEntry.getStressSequence());
            }
        }

        if (useTextgain) {
            if (wordMetre == null) {
                wordMetre = new WordMetre(new StressSequence(TextgainReader.numberOfSyllablesInString(word.getContent())));
            }
        }

        if (wordMetre == null) {
            wordMetre = new WordMetre();
        }

        return wordMetre;
    }

    private Collection<WordMetre> calculateMetresFromWords(Collection<? extends WordInterface> words) {
        Collection<WordMetre> wordMetres = new LinkedList<>();

        for (WordInterface word : words) {
            wordMetres.add(calculateMetreFromWord(word));
        }

        return wordMetres;
    }

    public LineMetre calculateMetreFromLine(LineInterface<? extends WordInterface> line) {
        Collection<? extends WordInterface> words = line.getWords();
        return new LineMetre(calculateMetresFromWords(words));
    }

    private Collection<LineMetre> calculateMetresFromLines(Collection<? extends LineInterface> lines) {
        Collection<LineMetre> lineMetres = new LinkedList<>();

        for (LineInterface line : lines) {
            lineMetres.add(calculateMetreFromLine(line));
        }

        return lineMetres;
    }

    public PassageMetre calculateMetreFromPassage(PassageInterface<? extends LineInterface> passage) {
        Collection<? extends LineInterface> lines = passage.getLines();
        return new PassageMetre(calculateMetresFromLines(lines));
    }

    // UNZIP

    public static PassageMetre unzipPassageMetre(AnalyzedPassage passage) {
        return unzipPassageMetre(passage.getLines());
    }

    public static PassageMetre unzipPassageMetre(Collection<AnalyzedLine> lines) {
        PassageMetre result = new PassageMetre();
        for (AnalyzedLine line : lines) {
            result.add(line.getMetre());
        }
        return result;
    }

    public static LineMetre unzipLineMetre(AnalyzedLine line) {
        return unzipLineMetre(line.getWords());
    }

    public static LineMetre unzipLineMetre(Collection<AnalyzedWord> words) {
        LineMetre result = new LineMetre();
        for (AnalyzedWord word : words) {
            result.add(word.getMetre());
        }
        return result;
    }
}
