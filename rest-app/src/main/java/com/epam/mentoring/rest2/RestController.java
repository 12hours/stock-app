package com.epam.mentoring.rest2;

//import com.epam.mentoring.data.model.Product;
//import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.EndpointInject;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
public class RestController {

    @EndpointInject(uri = "direct-vm:productService")
    ProductService productService;

    public RestController() {
//        this.productService = Mockito.mock(ProductService.class);
//        Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<Product>(){
//            {
//                add(new Product(1, "SomeProduct", BigDecimal.ZERO, null));
//            }
//        });
    }

    @GET
    @Path("/product")
    public Response getProducts() throws Exception{
        Class.forName(EndpointInject.class.getName());
        return Response.status(Response.Status.OK)
                        .type(MediaType.APPLICATION_JSON_TYPE)
//                        .entity(productService.getAllProducts())
                        .entity(new String("Hello world"))
                        .build();
    }

}
