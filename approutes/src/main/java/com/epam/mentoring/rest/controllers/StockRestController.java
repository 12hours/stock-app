package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.rest.Paths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Api(value = "Stock service", description = "Operations with stock")
@Path(Paths.STOCK_URI)
public class StockRestController {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of products on stock", responseContainer = "List", response = ProductWithQuantityView.class)
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Stock list returned.")
    })
    public Response getStockList() {
        return null;
    }
}
