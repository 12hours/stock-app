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

@Service
@Api(value = "Supplier service", description = "Operations on suppliers")
@Path(Paths.SUPPLIER_URI)
public class SupplierRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of all suppliers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of suppliers returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getAllSuppliers() {
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve supplier with specific id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Supplier object returned"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response getSupplierById(
            @ApiParam(value = "Supplier id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Save supplier object")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Supplier object saved"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response postSupplier(@ApiParam(value = "Supplier object", required = true) String body) {
        return null;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete supplier with specific id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Supplier deleted"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Response deleteSupplier(@ApiParam(value = "Supplier id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }

}
