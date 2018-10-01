package com.thatvineyard.snapswriter.session;

import com.thatvineyard.snapswriter.files.FileMapper;
import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.fitness.FitnessCalculator;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.metre.MetreCalculator;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ejb.Stateless;
import javax.ws.rs.QueryParam;

import static com.thatvineyard.snapswriter.writer.LyricFetcher.getAnalyzedPassage;
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

        AnalyzedPassage songPassage = writeSong(songId, textId);

        return formatter.passageToString(songPassage);
    }

    @Path("get-text")
    @GET
    public String getText(@QueryParam("text-id") String textId) {
        LOG.info("Getting text from textID: " + textId + ".");
        setUp();

        AnalyzedPassage analyzedSongPassage = getAnalyzedPassage(textId);

        return formatter.passageToString(analyzedSongPassage);
    }

    @Path("/example")
    @GET
    public String writeExampleSnapsSong() {
        LOG.info("Writing example snapssong.");
        setUp();

        AnalyzedPassage songPassage = writeSong("all-star", "communism");

        return formatter.passageToString(songPassage);
    }

    private AnalyzedPassage writeSong(String songId, String textId) {
        AnalyzedPassage analyzedSongPassage = getAnalyzedPassage(songId);
        AnalyzedPassage analyzedTextPassage = getAnalyzedPassage(textId);

        return matchSongAndTextPassage(analyzedSongPassage, analyzedTextPassage);
    }

    public void setUp() {
        formatter = new Formatter();
    }
}