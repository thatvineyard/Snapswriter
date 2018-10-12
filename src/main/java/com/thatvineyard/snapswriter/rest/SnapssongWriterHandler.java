package com.thatvineyard.snapswriter.rest;

import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.writer.LyricFetcher;
import com.thatvineyard.snapswriter.writer.Snapssong;
import com.thatvineyard.snapswriter.writer.SongWriter;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ejb.Stateless;

/**
 * SnapssongWriterHandler
 */
@Path("songwriter")
@Stateless
public class SnapssongWriterHandler {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    SongWriter songWriter;


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

    @Path("write-song")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Snapssong writeSnapsSongWithSongIdAndTextId(@QueryParam("song-id") String songId, Passage topic) {
        log.info("Writing snapssong with songId: " + songId + " and provided topic.");
        setUp();

        Snapssong snapssong = writeSong(songId, topic);

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
        return songWriter.writeSnapssong(songId, textId);
    }

    private Snapssong writeSong(String songId, Passage topic) {
        return songWriter.writeSnapssong(songId, topic);
    }

    public void setUp() {
        formatter = new Formatter();
    }
}
