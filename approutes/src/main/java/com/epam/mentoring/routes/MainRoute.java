package com.epam.mentoring.routes;

import com.epam.mentoring.routes.util.RequestObjectDataFormat;
import com.jayway.jsonpath.PathNotFoundException;
import my.soapjr.config.SoapjrObjectMapper;
import my.soapjr.model.RequestObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {

    @Autowired
    JacksonDataFormat requestObjectDataFormat;

    @Override
    public void configure() throws Exception {
        onException(PathNotFoundException.class).continued(true);
        onException(Exception.class).to(RouteNames.EXCEPTION_ROUTE);

        from(RouteNames.MAIN_ROUTE).routeId(RouteNames.MAIN_ROUTE_ID)
//                .unmarshal(requestObjectDataFormat)
//                .unmarshal(new JacksonDataFormat(new SoapjrObjectMapper(), RequestObject.class))
                .process(new Processor() {
                    // debug
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getContext().getTypeConverter());
                        System.out.println("Main route");
                        System.out.println("Body: "+exchange.getIn().getBody());
                        System.out.println("Headers: "+exchange.getIn().getHeaders());
                        System.out.println("Attachments: "+exchange.getIn().getAttachments());
                        System.out.println("Properties: "+exchange.getProperties());
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
