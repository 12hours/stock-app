package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductControllerTest {

    String PRODUCT_URI = Constants.URI_API_PREFIX + Constants.URI_PRODUCT;

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/asciidoc");

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ProductService productService;

    @Captor
    ArgumentCaptor<ProductForm> productFormArgumentCaptor;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void getProductViewsTest() throws Exception {
        List<Product> products = TestData.products();
        mockMvc.perform(get(PRODUCT_URI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(products.size())))
                .andExpect(jsonPath("$[0].id", equalTo(products.get(0).getId())))
                .andExpect(jsonPath("$[0].name", equalTo(products.get(0).getName())))
                .andExpect(jsonPath("$[0].productTypeId", equalTo(products.get(0).getType().getId())))
                // ugly conversion of BigInteger to Integer so jsonPath can perform comparsion
                .andExpect(jsonPath("$[0].price", equalTo(Integer.valueOf(products.get(0).getPrice().toString()))))
                .andDo(document("product-get"));;
        verify(productService, times(1)).getAllProductsAsViews();
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void saveProductFormTest() throws Exception {
        ProductForm productForm = new ProductForm("testProduct", BigDecimal.valueOf(100L), 1);
        mockMvc.perform(post(PRODUCT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productForm)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", equalTo("/product/10")))
                .andDo(document("product-save"));
        verify(productService, times(1)).saveProduct(Mockito.any(ProductForm.class));
        verify(productService).saveProduct(productFormArgumentCaptor.capture());
        assertEquals(productForm, productFormArgumentCaptor.getValue());

    }

}