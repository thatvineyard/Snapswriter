package com.thatvineyard.snapswriter.model.metre.calculator;

import com.thatvineyard.snapswriter.model.analysis.AnalyzedLine;
import com.thatvineyard.snapswriter.model.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.model.analysis.AnalyzedWord;
import com.thatvineyard.snapswriter.model.format.*;
import com.thatvineyard.snapswriter.model.metre.LineMetre;
import com.thatvineyard.snapswriter.model.metre.PassageMetre;
import com.thatvineyard.snapswriter.model.metre.StressSequence;
import com.thatvineyard.snapswriter.model.metre.WordMetre;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * MetreCalculator
 */
@Singleton
public class MetreCalculator {

    private Logger log = Logger.getLogger(this.getClass());

    public static final String cmuDictEnglish = "cmudict-0.7b.txt";
    public static final String cmuDictSwedish = "cmudict-svenska-0.1.txt";

    CmuDatabase database;

    private boolean useCmuDatabase = true;
    private boolean useTextgain = true;

    public MetreCalculator() {
    }

    public MetreCalculator(String dictionaryFilePath) {
        loadDatabase(dictionaryFilePath);
    }

    //
    @PostConstruct
    public void init() {
        useTextgain(false);
        loadDatabase();
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
        database = CmuReader.loadDictionariesFromFile(new LinkedList<>(Arrays.asList(cmuDictEnglish, cmuDictSwedish)));
    }

    public void loadDatabase(String dictionaryFilePath) {
        database = CmuReader.loadDictionaryFromFile(dictionaryFilePath);
    }

    // ANALYZER

    public AnalyzedWord analyzeWord(Word word) {
        return new AnalyzedWord(word, calculateMetreFromWord(word));
    }

    public AnalyzedLine analyzeLine(Line line) {
        return new AnalyzedLine(line, calculateMetreFromLine(line));
    }

    public AnalyzedPassage analyzePassage(Passage passage) {
        return new AnalyzedPassage(passage, calculateMetreFromPassage(passage));
    }

    // PLACEHOLDERS

    public static AnalyzedWord getPlaceholderAnalyzedWord(int syllables) {
        return new AnalyzedWord(new Word("<placeholder word>"), new WordMetre(new StressSequence(syllables)));
    }

    public static AnalyzedLine getPlaceholderAnalyzedLine(int syllables) {
        Collection<AnalyzedWord> words = new LinkedList<>();
        words.add(getPlaceholderAnalyzedWord(syllables));
        return new AnalyzedLine(words);
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
        log.info("Analyzing passage: " + passage.toString().substring(0, 10) + "...");
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
