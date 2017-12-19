package com.epam.mentoring.routes;

import com.epam.mentoring.routes.constants.RouteNames;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class NotFoundRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from(RouteNames.NOT_FOUND_ROUTE).routeId(RouteNames.NOT_FOUND_ID).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getOut().setBody("{'404':'Not found'}");
            }
        }).end();
    }
}