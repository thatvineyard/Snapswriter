package com.thatvineyard.snapswriter.session;

import com.thatvineyard.snapswriter.files.FileImporter;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;

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

    private static Formatter createFormatter() {
        return new Formatter(testDictionaryFilePath);
    }

    @Path("/")
    @GET
    public String simpleRestFunction() {
        Formatter formatter = createFormatter();
        FitnessCalculator calculator = new FitnessCalculator();

        String song = FileImporter.getFileText(allStarPath);
        String text = FileImporter.getFileText(communismPath);

        Passage songPassage = formatter.stringToPassage(song);
        Passage textPassage = formatter.stringToPassage(text);

        Passage newSongTextPassage = calculator.matchTextWithSong(textPassage, songPassage);

        String result = calculator.getScore() + "\n" + newSongTextPassage.toStringWithMetre();

        return result;
    }

}