package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRouteTest {

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductService productServiceMock;

    ArgumentCaptor<ProductForm> productFormArgumentCaptor;

    @Before
    public void setUp() {
        productFormArgumentCaptor = ArgumentCaptor.forClass(ProductForm.class);
    }

    @Test
    public void getAllProductsTest() throws Exception {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_ALL);
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_ROUTE, exchange);
        assertEquals(objectMapper.writeValueAsString( TestData.products()), response.getIn().getBody());
    }

    @Test
    public void getProductByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_ROUTE, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.products().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveProductTest() throws Exception {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.POST);
        in.setBody("{\"name\":\"testProduct\",\"price\":100,\"productTypeId\":1}");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_ROUTE, exchange);
        Mockito.verify(productServiceMock).saveProduct(productFormArgumentCaptor.capture());
        assertEquals(productFormArgumentCaptor.getValue(), new ProductForm("testProduct", BigDecimal.valueOf(100), Integer.valueOf(1)));
        assertEquals("{\"id\":42}", response.getIn().getBody());
    }

}