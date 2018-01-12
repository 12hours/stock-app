package com.epam.mentoring.web.controller;

import com.epam.mentoring.TestConfig;
import com.epam.mentoring.TestObjectData;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.web.StockAppWebApplication;
import com.epam.mentoring.web.client.ProductServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ActiveProfiles({"test", "controllerTest"})
@ContextConfiguration(classes = {TestConfig.class})
@ComponentScan(basePackageClasses = MainController.class)
public class MainControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    ProductServiceClient productServiceClient;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getMainPageTest() throws Exception {
        Collection<ProductWithQuantityView> expectedItems = TestObjectData.productWithQuantityViewCollectionDTO().getItems();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("productsWithQuantities", equalTo(expectedItems)));

        verify(productServiceClient, times(1)).findAllProductsWithQuantities();
    }
}