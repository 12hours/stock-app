package com.epam.mentoring.service.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainServiceRouteTest extends CamelTestSupport {

    @Produce(uri = RouteNames.MAIN_SERVICE_ROUTE)
    protected ProducerTemplate template;

    @Override
    public CamelContext context() {
        CamelContext context = super.context();
        try {
            context.addRoutes(new MainServiceRoute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

    @Test
    public void test() {
        Exchange exchange = createExchangeWithBody(new String("Hi"));
        template.send(exchange);
    }
}