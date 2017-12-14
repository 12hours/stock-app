package com.epam.mentoring.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ProductTypeRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_TYPE_ROUTE).routeId(RouteNames.PRODUCT_ROUTE_ID).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

            }
        }).end();
    }
}
