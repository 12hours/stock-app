package com.epam.mentoring.rest.controllers;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

//@Service
@Path("/rest")
public class ProductRestController {

    @EndpointInject(uri = "direct-vm:mainRoute")
    private ProducerTemplate mainRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product")
    public Response getProduct() {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity("Product get")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/product/")
    public Response postProduct(String body) throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        String answer = (String) mainRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
//                        .entity(productService.getAllProducts())
                .entity("Answer: " + answer)
                .build();
    }

}
