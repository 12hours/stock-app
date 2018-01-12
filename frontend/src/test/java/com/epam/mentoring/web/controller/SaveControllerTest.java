package com.epam.mentoring.web.controller;

import com.epam.mentoring.TestConfig;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.web.client.ProductServiceClient;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ActiveProfiles({"test", "controllerTest"})
@ContextConfiguration(classes = {TestConfig.class})
@ComponentScan(basePackageClasses = MainController.class)
public class SaveControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    ProductServiceClient productServiceClient;

    @Captor
    ArgumentCaptor<ProductForm> productFormArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveProductTest() throws Exception {
        ProductForm productForm = new ProductForm("testName", BigDecimal.valueOf(100), Integer.valueOf(2));
        mockMvc.perform(post("/add/product").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("name=testName&price=99&productTypeId=3"))
        ;
    }
}