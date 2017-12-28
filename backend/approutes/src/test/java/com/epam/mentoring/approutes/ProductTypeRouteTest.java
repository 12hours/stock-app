package com.epam.mentoring.approutes;

import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Value("${productType.route.endpoint}")
    private String productTypeRouteEndpoint;

    ArgumentCaptor<ProductTypeForm> productTypeFormArgumentCaptor;

    @Before
    public void setUp() {
        productTypeFormArgumentCaptor = ArgumentCaptor.forClass(ProductTypeForm.class);
    }

    @Test
    public void getAllProductTypesTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_GET_ALL);
        exchange.setIn(in);

        Exchange response = template.send(productTypeRouteEndpoint, exchange);
        System.out.println(response.getIn().getBody());
        assertEquals(objectMapper.writeValueAsString(TestData.productTypes()), response.getIn().getBody());
    }

    @Test
    public void getProductTypeByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(productTypeRouteEndpoint, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.productTypes().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveProductTypeTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_POST);
        in.setBody("{\"name\":\"testProductType\"}");
        exchange.setIn(in);

        Exchange response = template.send(productTypeRouteEndpoint, exchange);
        verify(productTypeServiceMock).saveProductType(productTypeFormArgumentCaptor.capture());
        assertEquals(productTypeFormArgumentCaptor.getValue(), new ProductTypeForm("testProductType"));
        assertEquals("{\"id\":43}", response.getIn().getBody());
    }

    @Test
    public void deleteProductTypeTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_DELETE);
        exchange.setIn(in);

        Exchange response = template.send(productTypeRouteEndpoint, exchange);
        verify(productTypeServiceMock, times(1)).deleteProductType(anyInt());
        assertEquals(Response.Status.OK, response.getIn().getHeader(Headers.STATUS));
    }
}