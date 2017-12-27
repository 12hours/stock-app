package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Headers;
import com.epam.mentoring.rest.Paths;
import com.epam.mentoring.rest.RouteNames;
import io.swagger.annotations.*;
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

@Api(value = "Product income service", description = "Operations on product incomes")
@Path(Paths.PRODUCT_INCOME_URI)
public class ProductIncomeRestController {

    Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_INCOME_ROUTE)
    ProducerTemplate productIncomeRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of all product incomes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of product incomes returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getAllProductIncomes() {
        log.debug("GET: {}", Paths.PRODUCT_INCOME_URI);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve product income with specific id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product income returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getProductIncomeById(@ApiParam(value = "Product income id", required = true)
            @PathParam("id") Integer id) {
        log.debug("GET: {}/{}", Paths.PRODUCT_INCOME_URI, id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Save product income")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product income saved"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response postProductIncome(@ApiParam(value = "Product income form object", required = true,
            example = "{'orderNumber':10000,'date':1513609404711,'quantity':128,'productId':1,'supplierId':2,'userId':3}")
                                                  String body) {
        log.debug("POST: {}", Paths.PRODUCT_INCOME_URI);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete product income with specific id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product income deleted"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response deleteProductIncome(@ApiParam(value = "Product income id", required = true) @PathParam("id") Integer id) {
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
