package com.thatvineyard.snapswriter.session;

import com.thatvineyard.snapswriter.files.FileImporter;
import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.metre.MetreCalculator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ejb.Stateless;

/**
 * ClientSessionBean
 */
@Path("/bean")
@Stateless
public class ClientSessionBean {

    private static final String resourceDir = "";
    private static final String testDictionaryFilePath = resourceDir + "cmudict-0.7b.txt";
    private static final String allStarPath = resourceDir + "all-star.txt";
    private static final String communismPath = resourceDir + "communism-wiki.txt";

    private Formatter createFormatter() {
        return new Formatter();
    }

    private MetreCalculator createCalculator() {
        return new MetreCalculator(testDictionaryFilePath);
    }

    @Path("/")
    @GET
    public String simpleRestFunction() {
        Formatter formatter = createFormatter();
        MetreCalculator metreCalculator = createCalculator();
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        String song = FileImporter.getFileText(allStarPath);
        String text = FileImporter.getFileText(communismPath);

        Passage songPassage = formatter.stringToPassage(song);
        Passage textPassage = formatter.stringToPassage(text);

        AnalyzedPassage analyzedSongPassage = new AnalyzedPassage(songPassage, metreCalculator);
        AnalyzedPassage analyzedTextPassage = new AnalyzedPassage(textPassage, metreCalculator);

        AnalyzedPassage newSongTextPassage = fitnessCalculator.matchTextWithSong(analyzedTextPassage, analyzedSongPassage);

        String result = fitnessCalculator.getScore() + "";
        result +="\n";
        result += formatter.passageToString(newSongTextPassage);

        return result;
    }

}