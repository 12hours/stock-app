package com.epam.mentoring.routes;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.directvm.DirectVmEndpoint;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;
//@MockEndpoints
public class MainRouteTest extends CamelSpringTestSupport{

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-context.xml");
        return context;
    }

    @Test
    public void test() throws Exception {
        MockEndpoint mockOut = getMockEndpoint("mock:test");
        context.getRouteDefinition(RouteNames.MAIN_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(RouteNames.PRODUCT_ROUTE).skipSendToOriginalEndpoint().to(mockOut);
            }
        });
        mockOut.setExpectedMessageCount(1);
        template.sendBody(RouteNames.MAIN_ROUTE, "{'HEAD':{'type':'product'}}");
        assertMockEndpointsSatisfied();
    }
}