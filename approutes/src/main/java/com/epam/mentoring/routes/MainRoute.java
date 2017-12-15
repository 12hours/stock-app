package com.epam.mentoring.routes;

import com.jayway.jsonpath.PathNotFoundException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MainRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(PathNotFoundException.class).continued(true);

        from(RouteNames.MAIN_ROUTE).routeId(RouteNames.MAIN_ROUTE_ID)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Main route");
                        System.out.println(exchange.getIn().getBody());
                    }
                })
                .choice()
                .when().jsonpath("$[?(@.HEAD.type=='product')]")
                   .to(RouteNames.PRODUCT_ROUTE)
                .when().jsonpath("$[?(@.HEAD.type=='supplier')]")
                    .to(RouteNames.SUPPLIER_ROUTE)
                .when().jsonpath("$[?(@.HEAD.type=='productType')]")
                    .to(RouteNames.PRODUCT_TYPE_ROUTE)
                .when().jsonpath("$[?(@.HEAD.type=='stock')]")
                    .to(RouteNames.STOCK_ROUTE)
                .otherwise()
                    .to(RouteNames.NOT_FOUND_ROUTE)
        ;
    }
}
