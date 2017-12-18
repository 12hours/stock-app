package com.epam.mentoring.rest.controllers;

import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public class ProductIncomeRestController {

    @GET
    @Path("/product_income")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductIncome() {

        return Response.status(Response.Status.OK)
                .entity("Get product income")
                .build();
    }

    @POST
    @Path("/product_income")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postProductIncome() {

        return Response.status(Response.Status.OK)
                .entity("Post product income")
                .build();
    }
}
