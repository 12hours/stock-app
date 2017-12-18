package com.epam.mentoring.rest.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest/stock")
public class StockRestController {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductIncome() {

        return Response.status(Response.Status.OK)
                .entity("Get stocklist")
                .build();
    }
}
