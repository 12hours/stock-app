package com.epam.mentoring.routes;

import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRoute extends RouteBuilder {

    @Autowired
    ProductService productService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_ROUTE).routeId(RouteNames.PRODUCT_ROUTE_ID)
                .choice()
                .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("Product route");
                exchange.getIn().setBody("products" + productService.getAllProducts());
            }
        }).end();
    }
}
