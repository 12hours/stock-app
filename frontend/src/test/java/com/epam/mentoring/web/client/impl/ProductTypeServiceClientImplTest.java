package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.TestConfig;
import com.epam.mentoring.TestObjectData;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.web.StockAppWebApplication;
import com.epam.mentoring.web.client.ProductTypeServiceClient;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockAppWebApplication.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class ProductTypeServiceClientImplTest {

    @Captor
    ArgumentCaptor<ProductTypeForm> productTypeFormArgumentCaptor;

    @Autowired
    RestTemplate restTemplateMock;

    @Autowired
    ProductTypeServiceClient productTypeServiceClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllProductTypesTest() {
        CollectionDTO<ProductTypeView> expectedDto = TestObjectData.productTypeViewCollectionDTO();
        CollectionDTO<ProductTypeView> actualDto = productTypeServiceClient.findAllProductTypes();

        verify(restTemplateMock, times(1)).getForEntity(eq("http://localhost:8081/api/product_type"), eq(String.class));
        assertNotNull(actualDto);
        assertNotNull(actualDto.getItems());
        assertEquals(expectedDto.getItems().size(), actualDto.getItems().size());
        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void saveProductTypeTest() {
        ProductTypeForm productTypeForm = new ProductTypeForm("testProductType");
        Integer id = productTypeServiceClient.saveProductType(productTypeForm);
        verify(restTemplateMock, times(1)).postForEntity(eq("http://localhost:8081/api/product_type"), productTypeFormArgumentCaptor.capture(), eq(String.class));
        assertEquals(productTypeForm, productTypeFormArgumentCaptor.getValue());
        assertEquals(Integer.valueOf(5), id);
    }
}