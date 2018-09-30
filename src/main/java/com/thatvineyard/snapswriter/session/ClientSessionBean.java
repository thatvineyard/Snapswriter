package com.thatvineyard.snapswriter.session;

import com.thatvineyard.snapswriter.files.FileImporter;
import com.thatvineyard.snapswriter.files.FileMapper;
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
@Path("/")
@Stateless
public class ClientSessionBean {


    private FileMapper createFilemapper() {
        return new FileMapper();
    }

    private Formatter createFormatter() {
        return new Formatter();
    }

    private MetreCalculator createCalculator() {
        MetreCalculator calculator = new MetreCalculator();
        calculator.useTextgain(false);
        return calculator;
    }

    @Path("/example")
    @GET
    public String simpleRestFunction() {
        FileMapper fileMapper = createFilemapper();
        Formatter formatter = createFormatter();
        MetreCalculator metreCalculator = createCalculator();
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        String song = FileImporter.getFileText(fileMapper.getFilepath("all-star"));
        String text = FileImporter.getFileText(fileMapper.getFilepath("communism"));

        Passage songPassage = formatter.stringToPassage(song);
        Passage textPassage = formatter.stringToPassage(text);

        AnalyzedPassage analyzedSongPassage = new AnalyzedPassage(songPassage, metreCalculator);
        AnalyzedPassage analyzedTextPassage = new AnalyzedPassage(textPassage, metreCalculator);

        AnalyzedPassage newSongTextPassage = fitnessCalculator.matchTextWithSong(analyzedTextPassage, analyzedSongPassage);

        String result = fitnessCalculator.getScore() + "";
        result +="\n";
        result += formatter.passageToString(newSongTextPassage);

        result += "\n";
        result += formatter.passageToString(analyzedSongPassage);

        return result;
    }

}