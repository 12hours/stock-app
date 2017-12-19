package com.epam.mentoring.approutes;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.service.ProductIncomeService;
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

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductIncomeRouteTest {

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductIncomeService productIncomeServiceMock;

    ArgumentCaptor<ProductIncomeForm> productIncomeFormArgumentCaptor;

    @Before
    public void setUp() {
        productIncomeFormArgumentCaptor = ArgumentCaptor.forClass(ProductIncomeForm.class);
    }

    @Test
    public void getAllProductIncomeByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_INCOME_ROUTE, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.productIncomes().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveProductIncomeTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.POST);
        in.setBody("{\"orderNumber\":10000,\"date\":1513609404711,\"quantity\":128,\"productId\":1,\"supplierId\":2,\"userId\":3}");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.PRODUCT_INCOME_ROUTE, exchange);
        Mockito.verify(productIncomeServiceMock).saveProductIncome(productIncomeFormArgumentCaptor.capture());
        assertEquals(new ProductIncomeForm(10000L, new Date(1513609404711L), 128, 1, 2, 3), productIncomeFormArgumentCaptor.getValue());
        assertEquals("{\"id\":45}", response.getIn().getBody());
    }


}