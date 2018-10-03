package com.thatvineyard.snapswriter.session;

import com.thatvineyard.snapswriter.files.FileMapper;
import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.metre.MetreCalculator;
import com.thatvineyard.snapswriter.writer.Song;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static com.thatvineyard.snapswriter.writer.LyricFetcher.getAnalyzedPassage;
import static com.thatvineyard.snapswriter.writer.LyricFetcher.getPassage;
import static com.thatvineyard.snapswriter.writer.SongMatcher.matchSongAndTextPassage;

/**
 * ClientSessionBean
 */
@Path("/")
@Stateless
public class ClientSessionBean {

    private Logger LOG = Logger.getLogger(this.getClass());

    Formatter formatter;

    @Path("write-song")
    @GET
    public String writeSnapsSongWithSongIdAndTextId(@QueryParam("song-id") String songId, @QueryParam("text-id") String textId) {
        LOG.info("Writing snapssong with songId: " + songId + " and textID: " + textId + ".");
        setUp();

        Song song = writeSong(songId, textId);

        return formatter.songToString(song);
    }

    @Path("get-text")
    @GET
    @Produces("application/json")
    public Passage getText(@QueryParam("text-id") String textId) {
        LOG.info("Getting text from textID: " + textId + ".");
        setUp();

        Passage passage = getPassage(textId);

        return passage;
    }

    @Path("get-text/as-string")
    @GET
    public String getTextAsString(@QueryParam("text-id") String textId) {
        LOG.info("Getting text from textID: " + textId + ".");
        setUp();

        Passage passage = getPassage(textId);

        return formatter.passageToString(passage);
    }


    @Path("get-analyzed-text")
    @GET
    @Produces("application/json")
    public AnalyzedPassage getAnalyzedText(@QueryParam("text-id") String textId) {
        LOG.info("Getting text from textID: " + textId + ".");
        setUp();

        AnalyzedPassage analyzedPassage = getAnalyzedPassage(textId);

        return analyzedPassage;
    }

    @Path("get-analyzed-text/as-string")
    @GET
    public String getAnalyzedTextAsString(@QueryParam("text-id") String textId) {
        LOG.info("Getting text from textID: " + textId + ".");
        setUp();

        AnalyzedPassage analyzedPassage = getAnalyzedPassage(textId);

        return formatter.passageToString(analyzedPassage);
    }


    @Path("/example")
    @GET
    public String writeExampleSnapsSong() {
        LOG.info("Writing example snapssong.");
        setUp();

        Song song = writeSong("all-star", "communism");

        return formatter.songToString(song);
    }

    private Song writeSong(String songId, String textId) {
        return new Song(songId, textId);
    }

    public void setUp() {
        formatter = new Formatter();
    }
}
