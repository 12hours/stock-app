package com.epam.mentoring.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest/stock")
public class StockController {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductIncome() {

        return Response.status(Response.Status.OK)
                .entity("Get stocklist")
                .build();
    }
}
