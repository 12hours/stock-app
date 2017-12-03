package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductIncomeControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/asciidoc");

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductIncomeService productIncomeService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @After
    public void reset() {

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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productIncomeForm)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andDo(document("product-income-save"));

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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productIncomeForm)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productIncomeForm)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                    .andDo(print())
                .andExpect(jsonPath("$['error']", is("Object validation failed")));
    }







}