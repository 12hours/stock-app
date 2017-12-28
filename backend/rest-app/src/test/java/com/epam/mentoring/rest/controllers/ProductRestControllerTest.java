package com.epam.mentoring.rest.controllers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRestControllerTest {

    private final static String ENDPOINT_ADDRESS = "http://localhost:8888/";
    private Server server;

    @Autowired
    ModelCamelContext camelContext;

    @Autowired
    ProductRestController productRestController;

    @Before
    public void setUp() throws Exception {
        System.out.println(productRestController);
        startServer();
    }

    @After
    public void cleanUp() throws Exception {
        server.stop();
        server.destroy();
    }

    private void startServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(ProductRestController.class);
        sf.setResourceProvider(ProductRestController.class,
                new SingletonResourceProvider(productRestController, false));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

    @Test
    public void test() throws Exception {
        camelContext.addRoutes( new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                from("direct-vm:productRoute").process(new Processor() {
                    @Override
                    public void process(org.apache.camel.Exchange exchange) throws Exception {
                        exchange.getIn().setBody("dummy");
                    }
                });
            }
        });

        Response response = RestAssured.get("http://localhost:8888/rest/product");
        response.then().assertThat().body(equalTo("dummy"));
    }
}