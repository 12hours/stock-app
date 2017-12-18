package com.epam.mentoring.rest;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public class SupplierRestController {

    @GET
    @Path("/supplier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupplier() {
        return Response.status(Response.Status.OK)
                .entity("Get supplier")
                .build();
    }

    @POST
    @Path("/supplier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSupplier() {
        return Response.status(Response.Status.OK)
                .entity("Post supplier")
                .build();
    }
}
