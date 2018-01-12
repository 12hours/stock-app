package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.TestConfig;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.web.StockAppWebApplication;
import com.epam.mentoring.web.client.ProductIncomeServiceClient;
import com.epam.mentoring.web.client.SupplierServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockAppWebApplication.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class ProductIncomeServiceClientImplTest {

    @Autowired
    ProductIncomeServiceClient productIncomeServiceClient;

    @Autowired
    RestTemplate restTemplateMock;

    @Captor
    ArgumentCaptor<ProductIncomeForm> productIncomeFormArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveProductIncomeTest() {
        ProductIncomeForm productIncomeForm = new ProductIncomeForm(9999L, new Date(System.currentTimeMillis()), Integer.valueOf(25), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(1));
        Integer id = productIncomeServiceClient.saveProductIncome(productIncomeForm);
        verify(restTemplateMock, times(1)).postForEntity(eq("http://localhost:8081/api/product_income"), productIncomeFormArgumentCaptor.capture(), eq(String.class));
        assertEquals(productIncomeForm, productIncomeFormArgumentCaptor.getValue());
        assertEquals(Integer.valueOf(18), id);
    }
}