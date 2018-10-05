package com.thatvineyard.snapswriter.session;

import com.thatvineyard.snapswriter.fitness.AnalyzedPassage;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.songcatalog.files.SongCatalog;
import com.thatvineyard.snapswriter.writer.LyricFetcher;
import com.thatvineyard.snapswriter.writer.Snapssong;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * ClientSessionBean
 */
@Path("/")
@Stateless
public class ClientSessionBean {

    @Inject
    SongCatalog songCatalog;
    @Inject
    LyricFetcher lyricFetcher;

    private Logger log = Logger.getLogger(this.getClass());

    Formatter formatter;

    @Path("write-song")
    @GET
    public String writeSnapsSongWithSongIdAndTextId(@QueryParam("song-id") String songId, @QueryParam("text-id") String textId) {
        log.info("Writing snapssong with songId: " + songId + " and textID: " + textId + ".");
        setUp();

        Snapssong snapssong = writeSong(songId, textId);

        return formatter.songToString(snapssong);
    }

    @Path("get-text")
    @GET
    @Produces("application/json")
    public Song getText(@QueryParam("text-id") String textId) {
        log.info("Getting text from textID: " + textId + ".");
        setUp();

        Song song = songCatalog.getSong(textId);

        return song;
    }

    @Path("get-text/as-string")
    @GET
    public String getTextAsString(@QueryParam("text-id") String textId) {
        log.info("Getting text from textID: " + textId + ".");
        setUp();

        Song song = songCatalog.getSong(textId);

        return formatter.passageToString(song);
    }


    @Path("get-analyzed-text")
    @GET
    @Produces("application/json")
    public AnalyzedPassage getAnalyzedText(@QueryParam("text-id") String textId) {
        log.info("Getting text from textID: " + textId + ".");
        setUp();

        AnalyzedPassage analyzedPassage = lyricFetcher.getAnalyzedPassage(textId);

        return analyzedPassage;
    }

    @Path("get-analyzed-text/as-string")
    @GET
    public String getAnalyzedTextAsString(@QueryParam("text-id") String textId) {
        log.info("Getting text from textID: " + textId + ".");
        setUp();

        AnalyzedPassage analyzedPassage = lyricFetcher.getAnalyzedPassage(textId);

        return formatter.passageToString(analyzedPassage);
    }


    @Path("/example")
    @GET
    public String writeExampleSnapsSong() {
        log.info("Writing example snapssong.");
        setUp();

        Snapssong snapssong = writeSong("all-star", "communism");

        return formatter.songToString(snapssong);
    }

    private Snapssong writeSong(String songId, String textId) {
        return Snapssong.writeSnapssong(lyricFetcher, songId, textId);
    }

    public void setUp() {
        formatter = new Formatter();
    }
}
