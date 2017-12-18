package com.epam.mentoring.rest2;

//import com.epam.mentoring.data.model.Product;
//import com.epam.mentoring.service.ProductService;

import com.epam.mentoring.service.ProductService;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
public class RestController {

    @EndpointInject(uri = "direct-vm:mainRoute")
    private ProducerTemplate mainRoute;

    ProductService productService;

    @Autowired
    public RestController(ProductService productService) {
        this.productService = productService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/product/")
    public Response getProducts(String body) throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        String answer = (String) mainRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
//                        .entity(productService.getAllProducts())
                .entity("Answer: " + answer)
                .build();
    }

}
