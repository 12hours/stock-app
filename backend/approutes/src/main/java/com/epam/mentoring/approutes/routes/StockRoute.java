package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StockRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from(RouteNames.STOCK_ROUTE).routeId(RouteNames.STOCK_ROUTE_ID).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

            }
        })
//                .choice()
//                .when(header(Headers.OPERATION).isEqualTo(Headers.STOCK_LIST))
//                    .setHeader(Headers.OPERATION, simple(Headers.PRODUCT_GET_ALL_WITH_QAUNT))
//                    .to("cxfrs:bean:productRestService")
                .end();
    }
}
