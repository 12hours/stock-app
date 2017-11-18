package com.epam.mentoring.web.controllers;

import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.client.IProductIncomeConsumer;
import com.epam.mentoring.client.IProductTypeConsumer;
import com.epam.mentoring.client.ISupplierConsumer;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.web.TestConfig;
import com.epam.mentoring.web.config.ThymeLeafConfig;
import com.epam.mentoring.web.config.WebConfig;
import com.epam.mentoring.web.forms.ProductForm;
import com.epam.mentoring.web.forms.ProductIncomeForm;
import com.epam.mentoring.web.forms.ProductTypeForm;
import com.epam.mentoring.web.forms.SupplierForm;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebConfig.class, ThymeLeafConfig.class})
@WebAppConfiguration
public class AddFormControllerTest {

    private MockMvc mockMvc;

    @Autowired
    IProductConsumer productConsumer;

    @Autowired
    IProductIncomeConsumer productIncomeConsumer;

    @Autowired
    IProductTypeConsumer productTypeConsumer;

    @Autowired
    ISupplierConsumer supplierConsumer;

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
    public void getAddProductIncomeFormPageTest() throws Exception {
        mockMvc.perform(get("/add/add-product-income"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product-income"))
                .andExpect(model().attribute("products", hasSize(3)))
                .andExpect(model().attribute("products", equalTo(referencedProductList)))
                .andExpect(model().attribute("productTypes", equalTo(referencedPorductTypeList)))
                .andExpect(model().attribute("suppliers", equalTo(referencedSupplierList)));
    }

    @Test
    public void postAddProductIncomeFormPageTest() throws Exception {
        mockMvc.perform(post("/add/add-product-income").flashAttr("productIncomeForm", new ProductIncomeForm())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("productId", "1"),
                        new BasicNameValuePair("quantity", "15"),
                        new BasicNameValuePair("orderNumber", "10001"),
                        new BasicNameValuePair("date", "2017-11-01"),
                        new BasicNameValuePair("supplierId", "2"),
                        new BasicNameValuePair("userId", "3")
                )))))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/stock"));

        ArgumentCaptor<ProductIncome> productIncomeArgumentCaptor = ArgumentCaptor.forClass(ProductIncome.class);
        Mockito.verify(productIncomeConsumer).addProductIncome(productIncomeArgumentCaptor.capture());
        assertEquals(Integer.valueOf(1), productIncomeArgumentCaptor.getValue().getProduct().getId());
        assertEquals(Integer.valueOf(2), productIncomeArgumentCaptor.getValue().getSupplier().getId());
        assertEquals(Integer.valueOf(3), productIncomeArgumentCaptor.getValue().getUser().getId());
        assertEquals(15, productIncomeArgumentCaptor.getValue().getQuantity());
        assertEquals(10001L, productIncomeArgumentCaptor.getValue().getOrderNumber());

        Calendar dateGoal = Calendar.getInstance();
        dateGoal.set(2017, 10, 1);
        Calendar dateExtracted = Calendar.getInstance();
        dateExtracted.setTime(productIncomeArgumentCaptor.getValue().getDate());
        assertEquals(dateGoal.get(dateGoal.YEAR), dateExtracted.get(dateExtracted.YEAR));
        assertEquals(dateGoal.get(dateGoal.MONTH), dateExtracted.get(dateExtracted.MONTH));
        assertEquals(dateGoal.get(dateGoal.DAY_OF_MONTH), dateExtracted.get(dateExtracted.DAY_OF_MONTH));

        Mockito.verify(productIncomeConsumer, times(1)).addProductIncome(Mockito.any(ProductIncome.class));
        Mockito.verifyNoMoreInteractions(productIncomeConsumer);
    }

//    @Test
//    public void test2() throws Exception {
//        mockMvc.perform(post("/add/add-product-income?productId=1&quantity=1&orderNumber=1&date=2017-11-01" +
//                "&supplierId=1&userId=1"));
//    }

    @Test
    public void getProductTypeFormPageTest() throws Exception {
        mockMvc.perform(get("/add/add-product-type"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product-type"));
    }

    @Test
    public void postProductTypeFormPageTest() throws Exception {
        mockMvc.perform(post("/add/add-product-type").flashAttr("productTypeForm", new ProductTypeForm())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("id", "1"),
                        new BasicNameValuePair("name", "RAM")
                )))))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/add"));

        ArgumentCaptor<ProductType> productTypeArgumentCaptor = ArgumentCaptor.forClass(ProductType.class);
        Mockito.verify(productTypeConsumer).saveProductType(productTypeArgumentCaptor.capture());
        assertEquals(null, productTypeArgumentCaptor.getValue().getId());
        assertEquals("RAM", productTypeArgumentCaptor.getValue().getName());
        Mockito.verify(productTypeConsumer, times(1)).saveProductType(Mockito.any(ProductType.class));
//        Mockito.verifyNoMoreInteractions(productTypeConsumer);
    }

    @Test
    public void getProductFormPageTest() throws Exception {
        mockMvc.perform(get("/add/add-product"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"))
                .andExpect(model().attribute("productTypes", equalTo(referencedPorductTypeList)));
    }

    @Test
    public void postProductFormPageTest() throws Exception {
        mockMvc.perform(post("/add/add-product").flashAttr("productForm", new ProductForm())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("name", "Ryzen"),
                        new BasicNameValuePair("price", "111.5"),
                        new BasicNameValuePair("productTypeId", "2")
                )))))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/add"));

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productConsumer).saveProduct(productArgumentCaptor.capture());
        assertEquals(null, productArgumentCaptor.getValue().getId());
        assertEquals("Ryzen", productArgumentCaptor.getValue().getProductName());
        assertEquals(BigDecimal.valueOf(111.5), productArgumentCaptor.getValue().getPrice());
        assertEquals(Integer.valueOf(2), productArgumentCaptor.getValue().getType().getId());
        Mockito.verify(productConsumer, times(1)).saveProduct(Mockito.any(Product.class));
        Mockito.verifyNoMoreInteractions(productConsumer);
    }


    @Test
    public void getSuplierFormPageTest() throws Exception {
        mockMvc.perform(get("/add/add-supplier"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-supplier"));
    }

    @Test
    public void postSupplierFormPageTest() throws Exception {
        mockMvc.perform(post("/add/add-supplier").flashAttr("supplierForm", new SupplierForm())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("name", "Dummy Supplier"),
                        new BasicNameValuePair("details", "Dummy Details")
                )))))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/add"));

        ArgumentCaptor<Supplier> supplierArgumentCaptor = ArgumentCaptor.forClass(Supplier.class);
        Mockito.verify(supplierConsumer).saveSupplier(supplierArgumentCaptor.capture());
        assertEquals(null, supplierArgumentCaptor.getValue().getId());
        assertEquals("Dummy Supplier", supplierArgumentCaptor.getValue().getName());
        assertEquals("Dummy Details", supplierArgumentCaptor.getValue().getDetails());
        Mockito.verify(supplierConsumer, times(1)).saveSupplier(Mockito.any(Supplier.class));
        Mockito.verifyNoMoreInteractions(supplierConsumer);
    }


}