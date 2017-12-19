package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.ProductTypeService;
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

import static org.junit.Assert.assertEquals;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductTypeRouteTest {

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductTypeService productTypeServiceMock;

    ArgumentCaptor<ProductTypeForm> productTypeFormArgumentCaptor;

    @Before
    public void setUp() {
        productTypeFormArgumentCaptor = ArgumentCaptor.forClass(ProductTypeForm.class);
    }

    @Test
    public void getAllProductTypesTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_ALL);
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        System.out.println(response.getIn().getBody());
        assertEquals(objectMapper.writeValueAsString(TestData.productTypes()), response.getIn().getBody());
    }

    @Test
    public void getProductTypeByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.productTypes().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveProductTypeTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.POST);
        in.setBody("{\"name\":\"testProductType\"}");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        Mockito.verify(productTypeServiceMock).saveProductType(productTypeFormArgumentCaptor.capture());
        assertEquals(productTypeFormArgumentCaptor.getValue(), new ProductTypeForm("testProductType"));
        assertEquals("{\"id\":43}", response.getIn().getBody());
    }


}