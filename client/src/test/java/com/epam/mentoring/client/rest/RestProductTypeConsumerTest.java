package com.epam.mentoring.client.rest;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
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
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withCreatedEntity;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RestProductTypeConsumerTest {

    @Spy
    RestTemplate restTemplate;

    @Captor
    ArgumentCaptor<ProductTypeForm> productTypeFormArgumentCaptor;

    RestProductTypeConsumer restProductTypeConsumer;

    MockRestServiceServer mockRestServiceServer;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        restProductTypeConsumer = new RestProductTypeConsumer(restTemplate);
    }

    @Test
    public void getAllProductTypes() throws Exception {
        List<ProductType> expectedProductTypes = TestData.productTypes();
        mockRestServiceServer.expect(requestTo("localhost:8080/product-type"))
                .andRespond(withSuccess()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(expectedProductTypes)));

        List<ProductType> actualProductTypes = restProductTypeConsumer.findAll();
        assertEquals(expectedProductTypes, actualProductTypes);
        verify(restTemplate, times(1)).exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class));


    }

    @Test
    public void saveProductType() {
        mockRestServiceServer.expect(requestTo("localhost:8080/product-type"))
                .andRespond(withCreatedEntity(URI.create("localhost:8080/product-type/10")));


        ProductTypeForm productType = new ProductTypeForm("testProductType");
        restProductTypeConsumer.saveProductType(productType);

        verify(restTemplate, times(1)).postForLocation(any(String.class), any(ProductTypeForm.class));
        verify(restTemplate).postForLocation(any(String.class), productTypeFormArgumentCaptor.capture());
        assertEquals(productType, productTypeFormArgumentCaptor.getValue());
    }

}