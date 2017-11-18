package com.epam.mentoring.web.controllers;


import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.client.IProductIncomeConsumer;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.web.TestConfig;
import com.epam.mentoring.web.config.ThymeLeafConfig;
import com.epam.mentoring.web.config.WebConfig;
import org.hamcrest.BaseMatcher;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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
public class StockControllerTest {

    private MockMvc mockMvc;

    @Autowired
    IProductConsumer productConsumer;

    @Autowired
    IProductIncomeConsumer productIncomeConsumer;

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
                .andExpect(model().attribute("productsWithQuantities", new CustomMatcher<Map>("Map size matcher") {
                    @Override
                    public boolean matches(Object item) {
                        return ((Map) item).size() == 3;
                    }
                }))
                .andExpect(model().attribute("productsWithQuantities", hasKey(
                        equalTo(referencedProductList.get(0))
                )))
                .andExpect(model().attribute("productsWithQuantities", hasKey(
                        equalTo(referencedProductList.get(1))
                )))
                .andExpect(model().attribute("productsWithQuantities", hasKey(
                        equalTo(referencedProductList.get(2))
                )));
        Mockito.verify(productConsumer, times(1)).getAllProductsWithQuantites();
        verifyNoMoreInteractions(productConsumer);
    }

    @Test
    public void getAddProductIncomeFormPageTest() throws Exception {
        mockMvc.perform(get("/add_product_income"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_product_income"))
                .andExpect(model().attribute("products", hasSize(3)))
                .andExpect(model().attribute("products", equalTo(referencedProductList)))
                .andExpect(model().attribute("productTypes", equalTo(referencedPorductTypeList)))
                .andExpect(model().attribute("suppliers", equalTo(referencedSupplierList)));
    }

    @Test
    public void postAddProductIncomeFormPageTest() throws Exception {
        MockHttpServletRequestBuilder post = post("/add_product_income");
        post.param("productId", "1");
        post.param("quantity", "50");
        post.param("supplierId", "2");
        post.param("userId", "3");
        post.param("orderNumber", "10001");
        post.param("date", "2017-11-01");
        mockMvc.perform(post);
        Mockito.verify(productIncomeConsumer, times(1)).addProductIncome(Mockito.any(ProductIncome.class));
    }

    @Test
    public void postAddProductFormPageTest() throws Exception {
        MockHttpServletRequestBuilder post = post("/add_product_income");
        post.param("productName", "Core i9");
        post.param("price", "500.0");
        post.param("productType", "1");
        mockMvc.perform(post);
        Mockito.verify(productConsumer, times(1)).saveProduct(Mockito.any(Product.class));
    }


}

