package com.epam.mentoring.web;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import org.junit.Test;

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
        assertEquals(10000L, productIncome.getOrderNumber());
        assertEquals(125, productIncome.getQuantity());
        assertEquals(Integer.valueOf(1), productIncome.getProduct().getId());
        assertEquals(Integer.valueOf(2), productIncome.getSupplier().getId());
        assertEquals(Integer.valueOf(3), productIncome.getUser().getId());
    }

}