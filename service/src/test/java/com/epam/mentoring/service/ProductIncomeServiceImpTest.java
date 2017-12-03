package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ProductIncomeDao;
import com.epam.mentoring.data.model.*;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.test.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductIncomeServiceImpTest {

    @Mock
    ProductIncomeDao dao;

    @Captor
    ArgumentCaptor<ProductIncome> productIncomeArgumentCaptor;

    ProductIncomeService productIncomeService;

    @Before
    public void setup() {
        when(dao.getAllProductIncomes()).thenReturn(TestData.productIncomes());
        when(dao.addProductIncome(any(ProductIncome.class))).thenReturn(10);
        productIncomeService = new ProductIncomeServiceImp(dao);
    }

    @Test
    public void getProductIncomeById() throws Exception {
    }

    @Test
    public void saveProductIncomeTest() throws Exception {
        Integer id = productIncomeService.saveProductIncome(TestData.productIncomes().get(0));
        assertEquals(Integer.valueOf(10), id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductIncomeInvalidProductFieldTest(){
        ProductIncome productIncome = TestData.productIncomes().get(0);
        productIncome.setProduct(null);
        productIncomeService.saveProductIncome(productIncome);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductIncomeInvalidUserFieldTest(){
        ProductIncome productIncome = TestData.productIncomes().get(0);
        productIncome.setUser(null);
        productIncomeService.saveProductIncome(productIncome);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductIncomeNullFieldsTest(){
        ProductIncome productIncome = new ProductIncome();
        productIncomeService.saveProductIncome(productIncome);

    }

    @Test
    public void saveProductIncomeFormTest() throws Exception {
        ProductIncomeForm productIncomeForm = TestData.productIncomeForms().get(0);
        Integer id = productIncomeService.saveProductIncome(productIncomeForm);
        assertEquals(Integer.valueOf(10), id);
        verify(dao, times(1)).addProductIncome(any(ProductIncome.class));
        verify(dao).addProductIncome(productIncomeArgumentCaptor.capture());
        assertEquals(DTOUtils.map(productIncomeForm, ProductIncome.class), productIncomeArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveProductIncomeFormIvalidFieldTest() throws Exception {
        ProductIncomeForm productIncomeForm = TestData.productIncomeForms().get(0);
        productIncomeForm.setUserId(null);
        Integer id = productIncomeService.saveProductIncome(productIncomeForm);
    }

}