package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.RouteNames;
import com.jayway.jsonpath.PathNotFoundException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {

    @Value("${product.route.endpoint}")
    private String productRouteEndpoint;

    @Value("${productIncome.route.endpoint}")
    private String productIncomeRouteEndpoint;

    @Value("${productType.route.endpoint}")
    private String productTypeRouteEndpoint;

    @Value("${stock.route.endpoint}")
    private String stockRouteEndpoint;

    @Value("${supplier.route.endpoint}")
    private String supplierRouteEndpoint;


    @Override
    public void configure() throws Exception {

        from(RouteNames.MAIN_ROUTE).routeId(RouteNames.MAIN_ROUTE_ID)
                .choice()
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(productRouteEndpoint))
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(productIncomeRouteEndpoint))
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(productTypeRouteEndpoint))
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(supplierRouteEndpoint))
                .end();
    }
}
