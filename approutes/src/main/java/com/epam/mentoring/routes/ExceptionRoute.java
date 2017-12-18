package com.epam.mentoring.routes;

import com.epam.mentoring.routes.constants.RouteNames;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExceptionRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(RouteNames.EXCEPTION_ROUTE).routeId(RouteNames.EXCEPTION_ROUTE_ID)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String message;
                        if (exchange.getException() != null) {
                            message = String.format("{'exception': '{}' }", exchange.getException().getMessage());
                        } else {
                            message = "{'exception':'unknown cause'}";
                        }
                        exchange.getOut().setBody(message);
                    }
                });
    }
}
