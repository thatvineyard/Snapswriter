package com.thatvineyard.snapswriter.rest.catalog;

import com.thatvineyard.snapswriter.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.catalog.passagecatalog.PassageCatalog;
import com.thatvineyard.snapswriter.format.Formatter;
import com.thatvineyard.snapswriter.format.Passage;
import com.thatvineyard.snapswriter.writer.LyricFetcher;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Path("passage-catalog")
@Stateless
public class PassageCatalogHandler {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    PassageCatalog passageCatalog;
    @Inject
    LyricFetcher   lyricFetcher;

    Formatter formatter;

    @Path("get-passage")
    @GET
    @Produces("application/json")
    public Passage getPassage(@QueryParam("passage-id") String passageId) {
        log.info("Getting passage from passageId: " + passageId + ".");
        setUp();

        Passage passage = passageCatalog.getPassage(passageId);

        return passage;
    }

    @Path("put-passage")
    @PUT
    @Consumes("application/json")
    public void putPassage(@QueryParam("passage-id") String passageId, Passage passage) {
        log.info("Putting passage into passage catalog under id " + passageId + ".");

        passageCatalog.putPassageInCache(passageId, passage);
    }

    @Path("get-passage/as-string")
    @GET
    public String getPassageAsString(@QueryParam("passage-id") String passageId) {
        log.info("Getting text from textID: " + passageId + ".");
        setUp();

        Passage passage = passageCatalog.getPassage(passageId);

        return formatter.passageToString(passage);
    }

    public void setUp() {
        formatter = new Formatter();
    }
}