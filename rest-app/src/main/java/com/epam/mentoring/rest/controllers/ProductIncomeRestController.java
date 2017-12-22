package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Headers;
import com.epam.mentoring.rest.Paths;
import com.epam.mentoring.rest.RouteNames;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path(Paths.PRODUCT_INCOME_URI)
public class ProductIncomeRestController {

    Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_INCOME_ROUTE)
    ProducerTemplate productIncomeRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductIncomes() {
        log.debug("GET: {}", Paths.PRODUCT_INCOME_URI);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductIncomeById(@PathParam("id") Integer id) {
        log.debug("GET: {}/{}", Paths.PRODUCT_INCOME_URI, id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postProductIncome(String body) {
        log.debug("POST: {}", Paths.PRODUCT_INCOME_URI);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProductIncome(@PathParam("id") Integer id) {
        log.debug("DELETE: {}/{}", Paths.PRODUCT_INCOME_URI, id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.DELETE);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) productIncomeRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }
}
