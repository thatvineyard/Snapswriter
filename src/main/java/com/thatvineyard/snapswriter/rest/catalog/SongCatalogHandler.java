package com.thatvineyard.snapswriter.rest.catalog;

import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Song;
import com.thatvineyard.snapswriter.catalog.songcatalog.SongCatalog;
import com.thatvineyard.snapswriter.writer.LyricFetcher;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Path("song-catalog")
@Stateless
public class SongCatalogHandler {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    SongCatalog songCatalog;
    @Inject
    LyricFetcher lyricFetcher;

    Formatter formatter;

    @Path("get-song")
    @GET
    @Produces("application/json")
    public Song getSong(@QueryParam("song-id") String songId) {
        log.info("Getting text from textID: " + songId + ".");
        setUp();

        Song song = songCatalog.getSong(songId);

        return song;
    }

    @Path("put-song")
    @PUT
    @Consumes("application/json")
    public void putSong(@QueryParam("song-id") String songId, Song song) {
        log.info("Putting song into song catalog under id " + songId + ".");

        songCatalog.putSongInCache(songId, song);
    }

    @Path("get-song/as-string")
    @GET
    public String getSongAsString(@QueryParam("song-id") String songId) {
        log.info("Getting text from textID: " + songId + ".");
        setUp();

        Song song = songCatalog.getSong(songId);

        return formatter.passageToString(song.getPassage());
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

    public void setUp() {
        formatter = new Formatter();
    }
}
