package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Headers;
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

@Path("/rest")
public class ProductIncomeRestController {

    Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_INCOME_ROUTE)
    ProducerTemplate productIncomeRoute;

    @GET
    @Path("/product_income")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductIncomes() {
        log.info("GET: /rest/product_income");
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }

    @GET
    @Path("/product_income/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductIncomeById(@PathParam("id") Integer id) {
        log.info("GET: /rest/product_income");
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Path("/product_income")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postProductIncome(String body) {
        log.info("POST: /rest/product_income");
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) productIncomeRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }
}
