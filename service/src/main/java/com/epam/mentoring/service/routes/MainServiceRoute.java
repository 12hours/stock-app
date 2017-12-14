package com.epam.mentoring.service.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MainServiceRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from(RouteNames.MAIN_SERVICE_ROUTE).routeId(RouteNames.MAIN_SERVICE_ID)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Process");
                        System.out.println(exchange.getIn().getBody().toString());
                    }
                });


    }
}
