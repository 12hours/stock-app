package com.epam.mentoring.approutes;

import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.mapstruct.SupplierMapper;
import com.epam.mentoring.data.model.dto.view.SupplierView;
import com.epam.mentoring.service.SupplierService;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SupplierRouteTest {

    private SupplierMapper supplierMapper = Mappers.getMapper(SupplierMapper.class);

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SupplierService supplierServiceMock;

    // TODO: replace occurences
    private String supplierRouteEndpoint = RouteNames.SUPPLIER_ROUTE;

    ArgumentCaptor<SupplierForm> supplierFormArgumentCaptor;
    ArgumentCaptor<Integer> integerArgumentCaptor;

    @Before
    public void setUp() {
        supplierFormArgumentCaptor = ArgumentCaptor.forClass(SupplierForm.class);
        integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    }

    @Test
    public void getAllSuppliersTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.SUPPLIER_GET_ALL);
        in.setHeader(Exchange.HTTP_METHOD, "GET");
        exchange.setIn(in);

        Exchange response = template.send(supplierRouteEndpoint, exchange);
        System.out.println(response.getIn().getBody());
        List<SupplierView> suppliers = TestData.suppliers().stream()
                .map(supplier -> supplierMapper.supplierToSupplierView(supplier))
                .collect(Collectors.toList());
        assertEquals(new CollectionDTO<>(suppliers), response.getIn().getBody());
    }

    @Test
    public void getSupplierByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.SUPPLIER_GET_BY_ID);
        in.setHeader(Exchange.HTTP_METHOD, "GET");
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(supplierRouteEndpoint, exchange);
        assertEquals(supplierMapper.supplierToSupplierView(TestData.suppliers().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveSupplierTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.SUPPLIER_POST);
        in.setHeader(Exchange.HTTP_METHOD, "POST");
        in.setBody("{\"name\":\"testSupplierName\",\"details\":\"testSupplierDetails\"}");
        exchange.setIn(in);

        Exchange response = template.send(supplierRouteEndpoint, exchange);
        verify(supplierServiceMock).saveSupplier(supplierFormArgumentCaptor.capture());
        assertEquals(supplierFormArgumentCaptor.getValue(), new SupplierForm("testSupplierName", "testSupplierDetails"));
        assertEquals(new HashMap<String,Object>(){{put("id", 44);}}, response.getIn().getBody());
    }

    @Test
    public void deleteSupplierTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.SUPPLIER_DELETE);
        in.setHeader(Exchange.HTTP_METHOD, "DELETE");
        exchange.setIn(in);

        Exchange response = template.send(supplierRouteEndpoint, exchange);
        verify(supplierServiceMock, times(1)).deleteSupplier(anyInt());
    }

    @Test
    public void getSupplierOfProductIncomeTest() {
        SupplierView expectedSupplier = supplierMapper.supplierToSupplierView(TestData.suppliers().get(1));
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.OPERATION, Headers.PRODUCT_INCOME_GET_SUPPLIER);
        in.setHeader(Exchange.HTTP_METHOD, "GET");
        in.setHeader(Headers.ID, 3);
        exchange.setIn(in);

        Exchange response = template.send(supplierRouteEndpoint, exchange);
        verify(supplierServiceMock, times(1)).getSupplierForProductIncome(anyInt());
        verify(supplierServiceMock).getSupplierForProductIncome(integerArgumentCaptor.capture());
        assertEquals(3, integerArgumentCaptor.getValue().intValue());
        assertEquals(expectedSupplier, response.getIn().getBody());
    }
}