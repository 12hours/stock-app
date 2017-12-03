package com.epam.mentoring.web.controllers;


import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.client.ProductIncomeConsumer;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.test.TestData;
import com.epam.mentoring.web.TestConfig;
import com.epam.mentoring.web.config.ThymeLeafConfig;
import com.epam.mentoring.web.config.WebConfig;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebConfig.class, ThymeLeafConfig.class})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StockControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ProductConsumer productConsumer;

    @Autowired
    ProductIncomeConsumer productIncomeConsumer;

    @Autowired
    @Qualifier("refProductList")
    List<Product> referencedProductList;

    @Autowired
    @Qualifier("refProductTypeList")
    private List<ProductType> referencedPorductTypeList;

    @Autowired
    @Qualifier("refSupplierList")
    private List<Supplier> referencedSupplierList;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getStockListTest() throws Exception {
        mockMvc.perform(get("/stocklist"))
                .andExpect(status().isOk())
                .andExpect(view().name("stocklist"))
//                .andExpect(forwardedUrl("/WEB-INF/templates/stocklist.html"))
//                .andExpect(model().attribute("productsWithQuantities", new CustomMatcher<Map>("Map size matcher") {
//                    @Override
//                    public boolean matches(Object item) {
//                        return ((Map) item).size() == 3;
//                    }
//                }))
                .andExpect(model().attribute("productsWithQuantitiesViews", equalTo(TestData.productWithQuantityViews())));
//                .andExpect(model().attribute("productsWithQuantities", hasKey(
//                        equalTo(referencedProductList.get(0))
//                )))
//                .andExpect(model().attribute("productsWithQuantities", hasKey(
//                        equalTo(referencedProductList.get(1))
//                )))
//                .andExpect(model().attribute("productsWithQuantities", hasKey(
//                        equalTo(referencedProductList.get(2))
//                )));
//        Mockito.verify(productConsumer, times(1)).getAllProductsWithQuantites();
        Mockito.verify(productConsumer, times(1)).getAllProductsWithQuantitiesViews();
        verifyNoMoreInteractions(productConsumer);
    }

}

