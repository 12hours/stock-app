package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductForm;
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

@Api(value = "Product service", description = "Operations on products")
@Path(Paths.PRODUCT_URI)
public class
ProductRestController {

    Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_ROUTE)
    private ProducerTemplate mainRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of all products")
    @ApiResponses(
            @ApiResponse(code = 200, message = "List of products returned")
    )
    public Response getAllProducts() {
        log.debug("GET: {}", Paths.PRODUCT_URI);
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @ApiOperation(value = "Retrieve product by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product found")
    )
    public Response getProduct(
            @ApiParam(value = "Product's id", required = true)
            @PathParam("id") Integer id) {
        log.debug("GET: {}/{}", Paths.PRODUCT_URI, id);
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Saves product")
    @ApiResponses(
            @ApiResponse(code = 201, message = "Product saved")
    )
    public Response postProduct(@ApiParam(value = "Product representation e.g.: {'name':'testProduct','price':100,'productTypeId':1}") String body) throws Exception {
        log.debug("POST: {}", Paths.PRODUCT_URI);
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Deletes product by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product found")
    )
    public Response deleteProductIncome(
            @ApiParam(value = "Product's id", required = true)
            @PathParam("id") Integer id) {
        log.info("DELETE: {}/{}", Paths.PRODUCT_URI, id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.DELETE);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) mainRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }

}
