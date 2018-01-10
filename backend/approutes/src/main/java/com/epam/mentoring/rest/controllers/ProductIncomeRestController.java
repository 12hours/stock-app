package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Paths;
import io.swagger.annotations.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Api(value = "Product income service", description = "Operations on product incomes")
@Path(Paths.PRODUCT_INCOME_URI)
public class ProductIncomeRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of all product incomes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of product incomes returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getAllProductIncomes() {
        return null;
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
        return null;
    }

    @GET
    @Path("/{id}/product")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve product of product income with specific id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getProductForProductIncome(@ApiParam(value = "Product income id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }

    @GET
    @Path("/{id}/supplier")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve supplier product income was supplied by")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Supplier returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getSupplierForProductIncome(@ApiParam(value = "Product income id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }

    @GET
    @Path("/{id}/user")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve user product income was submitted by")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getUserForProductIncome(@ApiParam(value = "Product income id", required = true)
            @PathParam("id") Integer id) {
        return null;
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
            example = "{'orderNumber':10000,'date':2017-05-21,'quantity':128,'productId':1,'supplierId':2,'userId':3}")
                                                  String body) {
        return null;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete product income with specific id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product income deleted"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response deleteProductIncome(@ApiParam(value = "Product income id", required = true) @PathParam("id") Integer id) {
        return null;
    }
}
