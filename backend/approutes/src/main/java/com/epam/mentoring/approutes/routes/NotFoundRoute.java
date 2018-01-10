package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.RouteNames;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class NotFoundRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from(RouteNames.NOT_FOUND_ROUTE).routeId(RouteNames.NOT_FOUND_ID)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("error debug");
                    }
                })
                .setBody(simple("{'404':'Not found'}"))
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .convertBodyTo(String.class)
                .end();
    }
}
