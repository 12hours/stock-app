package com.epam.mentoring.data.model;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductFormTest {

    @Test
    public void name() {
        ProductForm a = new ProductForm("test", BigDecimal.ZERO, 12);
        ProductForm b = new ProductForm("test", BigDecimal.ZERO, 12);
        a.equals(b);
    }
}