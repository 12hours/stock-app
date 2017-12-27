package com.epam.mentoring.approutes;

import com.epam.mentoring.approutes.constants.RouteNames;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@Ignore
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MainRouteTest{

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;



    @EndpointInject(uri = "mock:test", context = "serviceRoutesContext")
    MockEndpoint mockOut;


    @Before
    public void setup() throws Exception {
        context.getRouteDefinition(RouteNames.MAIN_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
//                weaveByToString("To[sdsdsd]").after().to(mock);
                interceptSendToEndpoint(RouteNames.PRODUCT_ROUTE).skipSendToOriginalEndpoint().to(mockOut);
            }
        });
    }

    @Test
    public void testMainToProductRouting() throws Exception {

        mockOut.setExpectedMessageCount(1);
        template.sendBody(RouteNames.MAIN_ROUTE, "{\"HEAD\":{\"type\":\"product\", \"method\":\"get\", \"id\":\"12\"}, \"BODY\":{}}");
        MockEndpoint.assertIsSatisfied(context);
    }

    @Test
    public void testMainToSupplierRouting() throws Exception {
        context.getRouteDefinition(RouteNames.MAIN_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(RouteNames.SUPPLIER_ROUTE).skipSendToOriginalEndpoint().to(mockOut);
            }
        });
        mockOut.setExpectedMessageCount(1);
        template.sendBody(RouteNames.MAIN_ROUTE, "{'HEAD':{'type':'supplier'}}");
        MockEndpoint.assertIsSatisfied(context);
    }

    @Test
    public void testMainToProductTypeRouting() throws Exception {
        context.getRouteDefinition(RouteNames.MAIN_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(RouteNames.PRODUCT_TYPE_ROUTE).skipSendToOriginalEndpoint().to(mockOut);
            }
        });
        mockOut.setExpectedMessageCount(1);
        template.sendBody(RouteNames.MAIN_ROUTE, "{'HEAD':{'type':'productType'}}");
        MockEndpoint.assertIsSatisfied(context);
    }

    @Test
    public void testMainToStockRouting() throws Exception {
        context.getRouteDefinition(RouteNames.MAIN_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(RouteNames.STOCK_ROUTE).skipSendToOriginalEndpoint().to(mockOut);
            }
        });
        mockOut.setExpectedMessageCount(1);
        template.sendBody(RouteNames.MAIN_ROUTE, "{'HEAD':{'type':'stock'}}");
        MockEndpoint.assertIsSatisfied(context);
    }

    @Test
    public void testMainToNotFoundRouting() throws Exception {
        context.getRouteDefinition(RouteNames.MAIN_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(RouteNames.NOT_FOUND_ROUTE).skipSendToOriginalEndpoint().to(mockOut);
            }
        });
        mockOut.setExpectedMessageCount(1);
        template.sendBody(RouteNames.MAIN_ROUTE, "{'HEAD':{'type':'dummy'}}");
        MockEndpoint.assertIsSatisfied(context);
    }



}