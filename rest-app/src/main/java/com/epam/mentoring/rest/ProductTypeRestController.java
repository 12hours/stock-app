package com.epam.mentoring.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest/product_type")
public class ProductTypeRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductIncome() {

        return Response.status(Response.Status.OK)
                .entity("Get product type income")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postProductIncome() {

        return Response.status(Response.Status.OK)
                .entity("Post product type income")
                .build();
    }
}
