package com.epam.mentoring.approutes;

import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.mapstruct.ProductTypeMapper;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
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
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductTypeRouteTest {

    ProductTypeMapper productTypeMapper = Mappers.getMapper(ProductTypeMapper.class);

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
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_GET_ALL);
        in.setHeader(Exchange.HTTP_METHOD, "GET");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        List<ProductTypeView> productTypes = TestData.productTypes().stream()
                                .map(productType -> productTypeMapper.productTypeToProductTypeView(productType))
                                .collect(Collectors.toList());
        assertEquals(new CollectionDTO<>(productTypes), response.getIn().getBody());
    }

    @Test
    public void getProductTypeByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        in.setHeader(Exchange.HTTP_METHOD, "GET");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        assertEquals(productTypeMapper.productTypeToProductTypeView(TestData.productTypes().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveProductTypeTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_POST);
        in.setHeader(Exchange.HTTP_METHOD, "POST");
        in.setBody("{\"name\":\"testProductType\"}");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        verify(productTypeServiceMock).saveProductType(productTypeFormArgumentCaptor.capture());
        assertEquals(productTypeFormArgumentCaptor.getValue(), new ProductTypeForm("testProductType"));
        assertEquals(new HashMap<String,Object>(){{put("id", Integer.valueOf(43));}}, response.getIn().getBody());
    }

    @Test
    public void deleteProductTypeTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_TYPE_DELETE);
        in.setHeader(Exchange.HTTP_METHOD, "DELETE");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_TYPE_ROUTE, exchange);
        verify(productTypeServiceMock, times(1)).deleteProductType(anyInt());
        assertEquals(Response.Status.OK, response.getIn().getHeader(Headers.STATUS));
    }
}