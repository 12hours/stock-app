package com.epam.mentoring.rest2;

//import com.epam.mentoring.data.model.Product;
//import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
public class RestController {

    @EndpointInject(uri = "direct-vm:mainRoute")
    private ProducerTemplate mainRoute;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/product")
    public Response getProducts(String body) throws Exception{
        String answer = (String) mainRoute.requestBody(body);
        return Response.status(Response.Status.OK)
                        .type(MediaType.APPLICATION_JSON_TYPE)
//                        .entity(productService.getAllProducts())
                        .entity("Hello world" + answer)
                        .build();
    }

}
