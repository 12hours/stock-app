package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.RouteNames;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

@Component
public class MainRoute extends RouteBuilder {

    @Value("${product.rest.endpoint}")
    private String productRestEndpoint;

    @Value("${productIncome.rest.endpoint}")
    private String productIncomeRestEndpoint;

    @Value("${productType.rest.endpoint}")
    private String productTypeRestEndpoint;

    @Value("${stock.rest.endpoint}")
    private String stockRestEndpoint;

    @Value("${supplier.rest.endpoint}")
    private String supplierRestEndpoint;


    @Override
    public void configure() throws Exception {

        onException(NotFoundException.class)
                .handled(true)
                .to(RouteNames.NOT_FOUND_ROUTE);

        onException(WebApplicationException.class)
                .handled(true)
                .to(RouteNames.EXCEPTION_ROUTE);

        from(   productRestEndpoint,
                productTypeRestEndpoint,
                productIncomeRestEndpoint,
                supplierRestEndpoint,
                stockRestEndpoint).routeId(RouteNames.MAIN_ROUTE_ID)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("debug");
                    }
                })
                .choice()
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(productRestEndpoint))
                        .to(RouteNames.PRODUCT_ROUTE)
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(productIncomeRestEndpoint))
                        .to(RouteNames.PRODUCT_INCOME_ROUTE)
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(productTypeRestEndpoint))
                        .to(RouteNames.PRODUCT_TYPE_ROUTE)
                    .when(exchange -> exchange.getFromEndpoint().getEndpointUri().equals(supplierRestEndpoint))
                        .to(RouteNames.SUPPLIER_ROUTE)
                    .otherwise()
                        .to(RouteNames.NOT_FOUND_ROUTE)
                .end()
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .marshal(new JsonDataFormat(JsonLibrary.Jackson))
                .convertBodyTo(String.class)
                .end();
    }
}
