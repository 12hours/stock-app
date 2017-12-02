package com.epam.mentoring.client.rest;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RestProductConsumerTest {

    RestProductConsumer restProductConsumer;

    MockRestServiceServer mockRestServiceServer;

    ObjectMapper objectMapper;

    @Spy
    RestTemplate restTemplate;

    @Before
    public void setup() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();


        restProductConsumer = new RestProductConsumer(restTemplate);
    }

    @Test
    public void getAllProductsWithQuantitiesViewsTest() throws JsonProcessingException {
        mockRestServiceServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/stock"))
                .andRespond(MockRestResponseCreators.withSuccess().contentType(MediaType.APPLICATION_JSON).body(
                        objectMapper.writeValueAsString(TestData.productWithQuantityViews())));

        List<ProductWithQuantityView> allProductsWithQuantitiesViews = restProductConsumer.getAllProductsWithQuantitiesViews();
        assertEquals(TestData.productWithQuantityViews(), allProductsWithQuantitiesViews);
        verify(restTemplate, times(1))
                .exchange(any(String.class), any(HttpMethod.class),any(HttpEntity.class), any(ParameterizedTypeReference.class));
//        verify(restTemplate, times(1)).getForObject(any(String.class), any(Class.class));
    }

    public class ResultCaptor<T> implements Answer {

        @Override
        public T answer(InvocationOnMock invocationOnMock) throws Throwable {
            T result = (T) invocationOnMock.callRealMethod();
            assertEquals(URI.create("http://localhost:8080/product/10"), result);
            return result;
        }
    }

    @Test
    public void saveProductFormTest() {
        mockRestServiceServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/product"))
                .andRespond(MockRestResponseCreators.withCreatedEntity(URI.create("http://localhost:8080/product/10")));
//        when(restTemplate.postForLocation(any(URI.class), any(Class.class))).thenAnswer(new ResultCaptor<URI>());
        doAnswer(new ResultCaptor<URI>()).when(restTemplate).postForLocation(any(URI.class), any(Class.class));


//        Product product = TestData.products().get(0);
        ProductForm productForm = new ProductForm("testProduct", BigDecimal.valueOf(100L), 1);
        restProductConsumer.saveProduct(productForm);
        verify(restTemplate, times(1)).postForLocation(any(URI.class), any(Class.class));

    }

    @Test
    public void getAllProductViewsTest() throws Exception {
        List<ProductView> productViews = new ArrayList<>();
        TestData.products().forEach(product -> {
            productViews.add(DTOUtils.map(product, ProductView.class));
        });

        mockRestServiceServer.expect(requestTo("http://localhost:8080/product"))
                .andRespond(withSuccess()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(productViews)));

        List<ProductView> actualProductViews = restProductConsumer.getAllProductViews();
        verify(restTemplate, times(1))
                .exchange(any(String.class), any(HttpMethod.class),any(HttpEntity.class), any(ParameterizedTypeReference.class));
//        assertTrue(actualProductViews.containsAll(productViews));
        assertThat(productViews, equalTo(actualProductViews));
    }

}