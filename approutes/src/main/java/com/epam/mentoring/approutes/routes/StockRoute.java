package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.RouteNames;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StockRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(RouteNames.STOCK_ROUTE).routeId(RouteNames.STOCK_ROUTE_ID).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

            }
        }).end();
    }
}
