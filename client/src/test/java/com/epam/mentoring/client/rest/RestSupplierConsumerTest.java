package com.epam.mentoring.client.rest;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

public class RestSupplierConsumerTest {
    @Spy
    RestTemplate restTemplate;

    @Captor
    ArgumentCaptor<Supplier> supplierArgumentCaptor;

    MockRestServiceServer mockRestServiceServer;

    RestSupplierConsumer restSupplierConsumer;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        restSupplierConsumer = new RestSupplierConsumer(restTemplate);
    }

    @Test
    public void saveSupplierTest() throws Exception {
        mockRestServiceServer.expect(requestTo("localhost:8080/supplier"))
                .andRespond(MockRestResponseCreators.withCreatedEntity(URI.create("localhost:8080/supplier/10")));

        SupplierForm supplierForm = new SupplierForm("testSupplierName", "testSupplierDetails");
        restSupplierConsumer.saveSupplier(supplierForm);
        verify(restTemplate, times(1)).postForLocation(any(String.class), any(SupplierForm.class));
        verify(restTemplate).postForLocation(any(String.class), supplierArgumentCaptor.capture());
        assertEquals(supplierForm, supplierArgumentCaptor.getValue());
    }

    @Test
    public void getAllSuppliersTest() throws Exception {
        List<Supplier> expectedSuppliers = TestData.suppliers();
        mockRestServiceServer.expect(requestTo("localhost:8080/supplier"))
                .andRespond(MockRestResponseCreators.withSuccess()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(expectedSuppliers)));

        List<Supplier> actualSuppliers = restSupplierConsumer.findAll();
        assertEquals(expectedSuppliers, actualSuppliers);
        verify(restTemplate, times(1)).exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class));
    }
}