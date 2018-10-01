package com.thatvineyard.snapswriter.session;

import com.sun.istack.NotNull;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * ClientSessionBean
 */
@Path("/")
@Stateless
public class ClientSessionBean {

    FileMapper fileMapper;
    Formatter formatter;
    MetreCalculator metreCalculator;
    FitnessCalculator fitnessCalculator;


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

    @Path("")
    @GET
    public String writeSnapsSongWithSongIdAndTextId(@QueryParam("song-id") String songId, @QueryParam("text-id") String textId) {
        setUp();

        AnalyzedPassage songPassage = matchSongIdWithTextId(songId, textId);

        return formatter.passageToString(songPassage);
    }

    @Path("/example")
    @GET
    public String writeExampleSnapsSong() {
        setUp();

        AnalyzedPassage songPassage = matchSongIdWithTextId("all-star", "communism");

        return formatter.passageToString(songPassage);
    }

    public AnalyzedPassage matchSongIdWithTextId(String songId, String textId) {
        String song = FileImporter.getFileText(fileMapper.getFilepath(songId));
        String text = FileImporter.getFileText(fileMapper.getFilepath(textId));

        Passage songPassage = formatter.stringToPassage(song);
        Passage textPassage = formatter.stringToPassage(text);

        AnalyzedPassage analyzedSongPassage = new AnalyzedPassage(songPassage, metreCalculator);
        AnalyzedPassage analyzedTextPassage = new AnalyzedPassage(textPassage, metreCalculator);

        AnalyzedPassage newSongTextPassage = fitnessCalculator.matchTextWithSong(analyzedTextPassage, analyzedSongPassage);

        return newSongTextPassage;
    }

    public void setUp() {
        fileMapper = createFilemapper();
        formatter = createFormatter();
        metreCalculator = createCalculator();
        fitnessCalculator = new FitnessCalculator();
    }
}