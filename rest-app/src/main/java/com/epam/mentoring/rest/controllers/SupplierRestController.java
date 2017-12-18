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

@Path("/rest")
public class SupplierRestController {

    Logger log = LoggerFactory.getLogger(SupplierRestController.class);

    @EndpointInject(uri = RouteNames.SUPPLIER_ROUTE)
    ProducerTemplate supplierRoute;

    @GET
    @Path("/supplier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSuppliers() {
        log.info("GET: /rest/supplier");
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }

    @GET
    @Path("/supplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupplierById(@PathParam("id") Integer id) {
        log.info("GET: /rest/supplier/" + id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Path("/supplier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSupplier(String body) {
        log.info("POST: /rest/supplier");
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) supplierRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }

}
