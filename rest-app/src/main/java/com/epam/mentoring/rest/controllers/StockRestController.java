package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.rest.Headers;
import com.epam.mentoring.rest.Paths;
import com.epam.mentoring.rest.RouteNames;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Service
@Api(value = "Stock service", description = "Operations with stock")
@Path(Paths.STOCK_URI)
public class StockRestController {

    Logger log = LoggerFactory.getLogger(StockRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_ROUTE)
    ProducerTemplate stockRoute;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of products on stock", responseContainer = "List", response = ProductWithQuantityView.class)
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Stock list returned.")
    })
    public Response getStockList() {
        log.debug("GET: {}/list", Paths.STOCK_URI);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL_WITH_QAUNT);
        return handleRequest(null, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) stockRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }
}
