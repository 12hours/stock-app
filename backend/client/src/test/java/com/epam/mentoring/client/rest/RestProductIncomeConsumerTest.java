package com.epam.mentoring.client.rest;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withCreatedEntity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestProductIncomeConsumerTest {

    @Spy
    RestTemplate restTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    MockRestServiceServer mockRestServiceServer;

    RestProductIncomeConsumer restProductIncomeConsumer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        restProductIncomeConsumer = new RestProductIncomeConsumer(restTemplate);
    }

    @Test
    public void saveProductIncomeTest() {
        mockRestServiceServer.expect(requestTo("http://localhost:8080/income"))
                .andRespond(withCreatedEntity(URI.create("http://localhost:8080/income/10")));
        doAnswer(invocation -> {
            URI result = (URI) invocation.callRealMethod();
            assertEquals("http://localhost:8080/income/10", result.toString());
            return result;
        }).when(restTemplate).postForLocation(any(String.class), any(ProductIncomeForm.class));

        ProductIncomeForm productIncomeForm = TestData.productIncomeForms().get(0);
        restProductIncomeConsumer.saveProductIncome(productIncomeForm);
        verify(restTemplate, times(1)).postForLocation(any(String.class), any(ProductIncomeForm.class));
    }

}