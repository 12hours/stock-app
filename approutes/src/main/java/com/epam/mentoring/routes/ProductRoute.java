package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductRoute extends RouteBuilder {

    @Autowired
    ProductService productService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_ROUTE).routeId(RouteNames.PRODUCT_ROUTE_ID)
                .choice()
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_ALL))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<Product> allProducts = productService.getAllProducts();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("products", allProducts);
                        exchange.getIn().setBody(map);
                        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
                    }
                })
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_BY_ID))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);

                        Product product = productService.findProductById(id);
                        HashMap<String, Product> productMap = new HashMap<>();
                        productMap.put("product", product);
                        exchange.getIn().setBody(productMap);
                        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
                    }
                })
                .when(header(Headers.METHOD).isEqualTo(Headers.POST))
                .unmarshal().json(JsonLibrary.Jackson, ProductForm.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        ProductForm productForm = (ProductForm) exchange.getIn().getBody();
                        Integer id = productService.saveProduct(productForm);
                        HashMap<String, Integer> idMap = new HashMap<>();
                        idMap.put("id", id);
                        exchange.getIn().setBody(idMap);
                        exchange.getIn().setHeader(Headers.STATUS, Response.Status.CREATED);
                    }
                })
                .otherwise().process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {

                    }
                })
                .end()
                .marshal(new JsonDataFormat(JsonLibrary.Jackson))
                .convertBodyTo(String.class)
                .end();
    }
}
