package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.rest.config.RestWebConfig;
import com.epam.mentoring.service.IProductIncomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestWebConfig.class, TestConfig.class})
@WebAppConfiguration
public class ProductIncomeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IProductIncomeService productIncomeService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void saveProductIncomeTest() throws Exception {
        ProductIncomeForm productIncomeForm = ProductIncomeForm.builder()
                .productId(1)
                .date(new Date())
                .orderNumber(10000L)
                .quantity(128)
                .supplierId(2)
                .userId(3)
                .build();
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/income")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(productIncomeForm)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
//                    .andDo(print());

        ArgumentCaptor<ProductIncomeForm> productIncomeFormArgumentCaptor = ArgumentCaptor.forClass(ProductIncomeForm.class);
        verify(productIncomeService).saveProductIncome(productIncomeFormArgumentCaptor.capture());
        Integer id = productIncomeService.saveProductIncome(productIncomeForm);
        assertEquals(productIncomeForm, productIncomeFormArgumentCaptor.getValue());
        assertEquals(Integer.valueOf(10), id);
    }

    @Test
    public void saveProductIncomeExpectErrorTest() throws Exception {
        ProductIncomeForm productIncomeForm = ProductIncomeForm.builder()
                .productId(42) // with id=42 saving should fail
                .date(new Date())
                .orderNumber(10000L)
                .quantity(128)
                .supplierId(2)
                .userId(3)
                .build();
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/income")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(productIncomeForm)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                    .andDo(print())
                    .andExpect(jsonPath("$['error']", is("Can not save object")));
    }

    @Test
    public void saveProductIncomeBadObjectExpectErrorTest() throws Exception {
        ProductIncomeForm productIncomeForm = ProductIncomeForm.builder()
                .productId(null) // No id: saving should fail
                .date(new Date())
                .orderNumber(10000L)
                .quantity(128)
                .supplierId(2)
                .userId(3)
                .build();
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/income")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(productIncomeForm)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                    .andDo(print())
                .andExpect(jsonPath("$['error']", is("Object validation failed")));
    }







}