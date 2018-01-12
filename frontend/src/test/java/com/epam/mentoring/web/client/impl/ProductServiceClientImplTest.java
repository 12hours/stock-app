package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.TestConfig;
import com.epam.mentoring.TestObjectData;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.web.StockAppWebApplication;
import com.epam.mentoring.web.client.ProductServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockAppWebApplication.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class ProductServiceClientImplTest {

    @Autowired
    ProductServiceClient productServiceClient;

    @Autowired
    RestTemplate restTemplateMock;

    @Captor
    ArgumentCaptor<ProductForm> productFormArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllProductsWithQuantitiesTest() {
        CollectionDTO<ProductWithQuantityView> expectedDto = TestObjectData.productWithQuantityViewCollectionDTO();

        CollectionDTO<ProductWithQuantityView> actualDto = productServiceClient.findAllProductsWithQuantities();

        assertNotNull(actualDto);
        assertNotNull(actualDto.getItems());
        assertEquals(expectedDto.getItems().size(), actualDto.getItems().size());
        assertTrue(actualDto.getItems().containsAll(expectedDto.getItems()));
        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void saveProductTest() {
        ProductForm testProduct = new ProductForm("testProduct", BigDecimal.valueOf(100), 1);
        Integer id = productServiceClient.saveProduct(testProduct);
        verify(restTemplateMock).postForEntity(eq("http://localhost:8081/api/product"), productFormArgumentCaptor.capture(), eq(String.class));
        assertEquals(Integer.valueOf(12), id);
    }

    @Test
    public void findAllProductsTest() {
        CollectionDTO<ProductView> expectedDto = TestObjectData.productViewCollectionDTO();
        CollectionDTO<ProductView> actualDto = productServiceClient.findAllProducts();
        verify(restTemplateMock, times(1)).getForEntity(eq("http://localhost:8081/api/product"), eq(String.class));
        assertNotNull(actualDto);
        assertNotNull(actualDto.getItems());
        assertEquals(expectedDto.getItems().size(), actualDto.getItems().size());
        assertEquals(expectedDto, actualDto );
    }
}