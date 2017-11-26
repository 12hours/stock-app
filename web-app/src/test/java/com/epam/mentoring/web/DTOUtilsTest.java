package com.epam.mentoring.web;

import com.epam.mentoring.data.model.*;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class DTOUtilsTest {

    @Test
    public void productIncomeFormToModelConversionTest() {
        Date date = new Date();
        ProductIncomeForm productIncomeForm = ProductIncomeForm.builder()
                .date(date)
                .orderNumber(10000L)
                .quantity(125)
                .productId(1)
                .supplierId(2)
                .userId(3)
                .build();

        ProductIncome productIncome = DTOUtils.map(productIncomeForm, ProductIncome.class);
        System.out.println(productIncome);
        assertEquals(date, productIncome.getDate());
        assertEquals(Long.valueOf(10000), productIncome.getOrderNumber());
        assertEquals(Integer.valueOf(125), productIncome.getQuantity());
        assertEquals(Integer.valueOf(1), productIncome.getProduct().getId());
        assertEquals(Integer.valueOf(2), productIncome.getSupplier().getId());
        assertEquals(Integer.valueOf(3), productIncome.getUser().getId());
    }

    @Test
    public void productIncomeToFormConversionTest(){
        ProductIncome productIncome = new ProductIncome();
        productIncome.setProduct(new Product(1, "testProduct", BigDecimal.valueOf(10L),
                new ProductType(1,"testProductType")));
        Date date = new Date();
        productIncome.setDate(date);
        productIncome.setOrderNumber(1000L);
        productIncome.setQuantity(128);
        productIncome.setSupplier(new Supplier(2, "testSupplier", "testDetails"));
        productIncome.setUser(new User(3, "username", "password", false));

        ProductIncomeForm form = DTOUtils.map(productIncome, ProductIncomeForm.class);
        assertEquals(Integer.valueOf(1), form.getProductId());
        assertEquals(date, form.getDate());
        assertEquals(Long.valueOf(1000), form.getOrderNumber());
        assertEquals(Integer.valueOf(128), form.getQuantity());
        assertEquals(Integer.valueOf(2), form.getSupplierId());
        assertEquals(Integer.valueOf(3), form.getUserId());

    }

}