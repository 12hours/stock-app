package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.service.IProductTypeService;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductTypeControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/asciidoc");

    MockMvc mockMvc;

    @Autowired
    IProductTypeService productTypeServiceMock;

    ArgumentCaptor<ProductTypeForm> productTypeFormArgumentCaptor;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        this.productTypeFormArgumentCaptor = ArgumentCaptor.forClass(ProductTypeForm.class);
    }

    @Test
    public void getProductTypesTest() throws Exception {
        List<ProductType> productTypesExpected = TestData.productTypes();

        mockMvc.perform(get("/product-type"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(productTypesExpected.size())))
                .andExpect(jsonPath("$[0].id", is(productTypesExpected.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(productTypesExpected.get(0).getName())))
                .andDo(document("product-type-get-all"));
        verify(productTypeServiceMock, times(1)).getAllProductTypes();

    }

    @Test
    public void saveProductTypeFormTest() throws Exception {
        ProductTypeForm productType = new ProductTypeForm("testProductType");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/product-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productType)))
                .andExpect(status().isCreated())
                .andDo(document("product-type-save"));
//        verify(productTypeServiceMock, times(1)).saveProductType(any(ProductType.class));
        verify(productTypeServiceMock).saveProductType(productTypeFormArgumentCaptor.capture());
        assertEquals(productType, productTypeFormArgumentCaptor.getValue());
    }

    @Test
    public void saveProductTypeFormInvalidTest() throws Exception {
        ProductTypeForm productTypeForm = new ProductTypeForm();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/product-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productTypeForm)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['error']", CoreMatchers.is("Object validation failed")));
    }
}