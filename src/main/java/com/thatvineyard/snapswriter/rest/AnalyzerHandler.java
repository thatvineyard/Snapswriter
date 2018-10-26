package com.thatvineyard.snapswriter.rest;

import com.thatvineyard.snapswriter.model.analysis.AnalyzedPassage;
import com.thatvineyard.snapswriter.writer.LyricFetcher;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("analyzer")
public class AnalyzerHandler {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    LyricFetcher lyricFetcher;


    @Path("analyze-passage")
    @GET
    @Produces("application/json")
    public AnalyzedPassage analyzePassage(@QueryParam("passage-id") String passageId) {
        log.info("Analyzing passage with passageId: " + passageId + ".");
        setUp();

        return lyricFetcher.getAnalyzedPassage(passageId);
    }

    public void setUp() {
    }

}
