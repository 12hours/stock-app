package com.epam.mentoring.approutes;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.approutes.constants.Headers;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@PropertySource("classpath:/test-application.properties")
public class ProductRouteTest {

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductService productServiceMock;

    @Value("${product.route.endpoint}")
    private String productRouteEndpoint;

    ArgumentCaptor<ProductForm> productFormArgumentCaptor;

    @Before
    public void setUp() {
        productFormArgumentCaptor = ArgumentCaptor.forClass(ProductForm.class);
    }

    @Test
    public void getAllProductsTest() throws Exception {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_GET_ALL);
        exchange.setIn(in);

        Exchange response = template.send(productRouteEndpoint, exchange);
        assertEquals(objectMapper.writeValueAsString( TestData.products()), response.getIn().getBody());
    }

    @Test
    public void getAllProductsWithQuantitiesTest() throws Exception {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_GET_ALL_WITH_QAUNT);
        exchange.setIn(in);

        Exchange response = template.send(productRouteEndpoint, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.productWithQuantityViews()), response.getIn().getBody());
    }

    @Test
    public void getProductByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(productRouteEndpoint, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.products().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveProductTest() throws Exception {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_POST);
        in.setBody("{\"name\":\"testProduct\",\"price\":100,\"productTypeId\":1}");
        exchange.setIn(in);

        Exchange response = template.send(productRouteEndpoint, exchange);
        verify(productServiceMock).saveProduct(productFormArgumentCaptor.capture());
        assertEquals(productFormArgumentCaptor.getValue(), new ProductForm("testProduct", BigDecimal.valueOf(100), Integer.valueOf(1)));
        assertEquals("{\"id\":42}", response.getIn().getBody());
    }

    @Test
    public void deleteProductTest() {
        Exchange exchange = new DefaultExchange(context);
        DefaultMessage in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_DELETE);
        exchange.setIn(in);

        Exchange response = template.send(productRouteEndpoint, exchange);
        verify(productServiceMock, times(1)).deleteProductById(anyInt());
        assertEquals(Response.Status.OK, response.getIn().getHeader(Headers.STATUS));
    }
}