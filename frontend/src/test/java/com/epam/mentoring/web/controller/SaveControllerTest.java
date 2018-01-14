package com.epam.mentoring.web.controller;

import com.epam.mentoring.TestConfig;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.web.client.ProductIncomeServiceClient;
import com.epam.mentoring.web.client.ProductServiceClient;
import com.epam.mentoring.web.client.ProductTypeServiceClient;
import com.epam.mentoring.web.client.SupplierServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ActiveProfiles({"test", "controllerTest"})
@ContextConfiguration(classes = {TestConfig.class})
@ComponentScan(basePackageClasses = MainController.class)
public class SaveControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    ProductServiceClient productServiceClient;

    @Autowired
    ProductTypeServiceClient productTypeServiceClient;

    @Autowired
    ProductIncomeServiceClient productIncomeServiceClient;

    @Autowired
    SupplierServiceClient supplierServiceClient;

    @Captor
    ArgumentCaptor<ProductForm> productFormArgumentCaptor;

    @Captor
    ArgumentCaptor<ProductTypeForm> productTypeFormArgumentCaptor;

    @Captor
    ArgumentCaptor<ProductIncomeForm> productIncomeFormArgumentCaptor;

    @Captor
    ArgumentCaptor<SupplierForm> supplierFormArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveProductTest() throws Exception {
        ProductForm productForm = new ProductForm("testName", BigDecimal.valueOf(99), Integer.valueOf(3));
        mockMvc.perform(post("/add/product").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("name=testName&price=99&productTypeId=3"))
        ;

        verify(productServiceClient, times(1)).saveProduct(productFormArgumentCaptor.capture());
        assertEquals(productForm, productFormArgumentCaptor.getValue());
    }

    @Test
    public void saveProductTypeTest() throws Exception {
        ProductTypeForm productTypeForm = new ProductTypeForm("testType");
        mockMvc.perform(post("/add/type").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("name=testType"));

        verify(productTypeServiceClient, times(1)).saveProductType(productTypeFormArgumentCaptor.capture());
        assertEquals(productTypeForm, productTypeFormArgumentCaptor.getValue());
    }

    @Test
    public void saveProductIncomeTest() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, 0, 15);
        ProductIncomeForm productIncomeForm = new ProductIncomeForm(100L, cal.getTime(), 12, 2, 1, 4);
        mockMvc.perform(post("/add/income").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("orderNumber=100&date=2018-01-15&quantity=12&productId=2&supplierId=1&userId=4")
        );

        verify(productIncomeServiceClient, times(1)).saveProductIncome(productIncomeFormArgumentCaptor.capture());
        assertEquals(productIncomeForm, productIncomeFormArgumentCaptor.getValue());
    }
}