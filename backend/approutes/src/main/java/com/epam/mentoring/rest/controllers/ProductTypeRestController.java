package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Paths;
import io.swagger.annotations.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Api(value = "Product type service", description = "Operations on product types")
@Path(Paths.PRODUCT_TYPE_URI)
public class ProductTypeRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve all product types")
    @ApiResponses(
            @ApiResponse(code = 200, message = "List of product types returned")
    )
    public Response getAllProductTypes() {
        return null;
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
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Save product type")
    @ApiResponses(
        @ApiResponse(code = 201, message = "Product type saved")
    )
    public Response postProductType(@ApiParam(value = "Product type object", required = true) String body) {
        return null;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete product type with specific id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product type deleted")
    )
    public Response deleteProductType(@ApiParam(value = "Product type id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }
}
