package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ProductTypeDao;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.test.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductTypeServiceImpTest {

    @Mock
    ProductTypeDao dao;

    @Captor
    ArgumentCaptor<ProductType> productTypeArgumentCaptor;

    ProductTypeService productTypeService;

    @Before
    public void setup() {
        when(dao.getAllProductTypes()).thenReturn(TestData.productTypes());
        when(dao.addProductType(any(ProductType.class))).thenReturn(10);
        productTypeService = new ProductTypeServiceImpl(dao);
    }

    @Test
    public void getAllProductTypesTest() {
        List<ProductType> allProductTypes = productTypeService.getAllProductTypes();
        assertNotNull(allProductTypes);
        assertEquals(TestData.productTypes(), allProductTypes);
        verify(dao, times(1)).getAllProductTypes();
    }

    @Test
    public void saveProductTypeTest() {
        ProductType productType = TestData.productTypes().get(0);
        Integer id = productTypeService.saveProductType(productType);
        assertEquals(Integer.valueOf(10), id);
        verify(dao, times(1)).addProductType(any(ProductType.class));
        verify(dao).addProductType(productTypeArgumentCaptor.capture());
        assertEquals(productType, productTypeArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductTypeInvalidFieldTest() {
        ProductType productType = TestData.productTypes().get(0);
        productType.setName(null);
        Integer id = productTypeService.saveProductType(productType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductTypeNullTest() {
        ProductType productType = null;
        Integer id = productTypeService.saveProductType(productType);
    }

    @Test
    public void saveProductTypeFormTest() {
        ProductType productType = TestData.productTypes().get(0);
        ProductTypeForm form = DTOUtils.map(productType, ProductTypeForm.class);
        Integer id = productTypeService.saveProductType(form);
        assertEquals(Integer.valueOf(10), id);
        verify(dao, times(1)).addProductType(any(ProductType.class));
        verify(dao).addProductType(productTypeArgumentCaptor.capture());
        assertEquals(new ProductType(null, productType.getName()), productTypeArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductTypeFormNullNameTest() {
        ProductType productType = new ProductType();
        ProductTypeForm form = DTOUtils.map(productType, ProductTypeForm.class);
        Integer id = productTypeService.saveProductType(form);
    }





}