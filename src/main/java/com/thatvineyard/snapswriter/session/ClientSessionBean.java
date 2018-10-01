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

    @Path("write-song")
    @GET
    public String writeSnapsSongWithSongIdAndTextId(@QueryParam("song-id") String songId, @QueryParam("text-id") String textId) {
        setUp();

        AnalyzedPassage songPassage = matchSongIdWithTextId(songId, textId);

        return formatter.passageToString(songPassage);
    }

    @Path("get-text")
    @GET
    public String getText(@QueryParam("text-id") String textId) {
        setUp();

        AnalyzedPassage analyzedSongPassage = getAnalyzedPassage(textId);

        return formatter.passageToString(analyzedSongPassage);
    }

    @Path("/example")
    @GET
    public String writeExampleSnapsSong() {
        setUp();

        AnalyzedPassage songPassage = matchSongIdWithTextId("all-star", "communism");

        return formatter.passageToString(songPassage);
    }

    private AnalyzedPassage matchSongIdWithTextId(String songId, String textId) {
        AnalyzedPassage analyzedSongPassage = getAnalyzedPassage(songId);
        AnalyzedPassage analyzedTextPassage = getAnalyzedPassage(textId);

        AnalyzedPassage newSongTextPassage = fitnessCalculator.matchTextWithSong(analyzedTextPassage, analyzedSongPassage);

        return newSongTextPassage;
    }

    private AnalyzedPassage getAnalyzedPassage(String fileId) {
        String song = FileImporter.getFileText(fileMapper.getFilepath(fileId));

        Passage songPassage = formatter.stringToPassage(song);

        return new AnalyzedPassage(songPassage, metreCalculator);
    }

    public void setUp() {
        fileMapper = createFilemapper();
        formatter = createFormatter();
        metreCalculator = createCalculator();
        fitnessCalculator = new FitnessCalculator();
    }
}