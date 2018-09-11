package com.thatvineyard.snapswriter.session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ejb.Stateless;

/**
 * ClientSessionBean
 */
@Path("/bean")
@Stateless
public class ClientSessionBean {

    @Path("/")
    @GET
    public String simpleRestFunction() {
        return "Hello";
    }

}