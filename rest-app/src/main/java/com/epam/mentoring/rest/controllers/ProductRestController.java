package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Headers;
import com.epam.mentoring.rest.RouteNames;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

//@Service
@Path("/rest")
public class
ProductRestController {


    static {
        org.slf4j.MDC.put("app.name","rest-app");
    }

    Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_ROUTE)
    private ProducerTemplate mainRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product")
    public Response getAllProducts() {
        log.debug("GET: /rest/product");
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
//        return Response.status(Response.Status.OK).build();
        return handleRequest(null, headers);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/{id}")
    public Response getProduct(@PathParam("id") Integer id) {
        log.debug("GET: /rest/product/" + id);
        final Map<String, Object> headers = new HashMap<>();
        System.out.println(id == null);
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
//        return Response.status(Response.Status.OK).build();
        return handleRequest(null, headers);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/product/")
    public Response postProduct(String body) throws Exception {
        log.debug("POST: /rest/product");
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) mainRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }

}
