package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Headers;
import com.epam.mentoring.rest.Paths;
import com.epam.mentoring.rest.RouteNames;
import io.swagger.annotations.*;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Product type service", description = "Operations on product types")
@Path(Paths.PRODUCT_TYPE_URI)
public class ProductTypeRestController {

    Logger log = LoggerFactory.getLogger(ProductTypeRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_TYPE_ROUTE)
    ProducerTemplate productTypeRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve all product types")
    @ApiResponses(
            @ApiResponse(code = 200, message = "List of product types returned")
    )
    public Response getAllProductTypes() {
        log.debug("GET: {}", Paths.PRODUCT_TYPE_URI);
        Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve product type with specific id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product type object returned")
    )
    public Response getProductTypeById(@ApiParam(value = "Product type id" ,required = true)
            @PathParam("id") Integer id) {
        log.debug("GET: {}/{}", Paths.PRODUCT_TYPE_URI, id);
        Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Save product type")
    @ApiResponses(
        @ApiResponse(code = 201, message = "Product type saved")
    )
    public Response postProductType(@ApiParam(value = "Product type object", required = true) String body) {
        log.debug("POST: {}", Paths.PRODUCT_TYPE_URI);
        Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete product type with specific id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product type deleted")
    )
    public Response deleteProductIncome(@ApiParam(value = "Product type id", required = true)
            @PathParam("id") Integer id) {
        log.debug("DELETE: {}/{}", Paths.PRODUCT_TYPE_URI, id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.DELETE);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) productTypeRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }
}
