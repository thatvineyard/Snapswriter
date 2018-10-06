package com.thatvineyard.snapswriter.rest;

import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
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
 * SnapssongWriterHandler
 */
@Path("songwriter")
@Stateless
public class SnapssongWriterHandler {

    @Inject
    LyricFetcher lyricFetcher;

    private Logger log = Logger.getLogger(this.getClass());

    Formatter formatter;

    @Path("write-song")
    @GET
    @Produces("application/json")
    public Snapssong writeSnapsSongWithSongIdAndTextId(@QueryParam("song-id") String songId, @QueryParam("text-id") String textId) {
        log.info("Writing snapssong with songId: " + songId + " and textID: " + textId + ".");
        setUp();

        Snapssong snapssong = writeSong(songId, textId);

        return snapssong;
    }

    @Path("example")
    @GET
    @Produces("application/json")
    public Snapssong writeExampleSnapsSong() {
        log.info("Writing example snapssong.");
        setUp();

        Snapssong snapssong = writeSong("all-star", "communism");

        return snapssong;
    }

    private Snapssong writeSong(String songId, String textId) {
        return Snapssong.writeSnapssong(lyricFetcher, songId, textId);
    }

    public void setUp() {
        formatter = new Formatter();
    }
}
