package com.epam.mentoring.approutes;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.ProductTypeService;
import com.epam.mentoring.service.SupplierService;
import com.epam.mentoring.test.TestData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class TestConfig {

    @Bean
    ProductService productService() {
        ProductService productService = mock(ProductService.class);
        when(productService.getAllProducts()).thenReturn(TestData.products());
        when(productService.getAllProductsWithQuantitiesViews()).thenReturn(TestData.productWithQuantityViews());
        when(productService.findProductById(anyInt())).thenReturn(TestData.products().get(0));
        when(productService.saveProduct(any(ProductForm.class))).thenReturn(42);
        return productService;
    }

    @Bean
    ProductTypeService productTypeService() {
        ProductTypeService productTypeService = mock(ProductTypeService.class);
        when(productTypeService.getAllProductTypes()).thenReturn(TestData.productTypes());
        when(productTypeService.getProductTypeById(anyInt())).thenReturn(TestData.productTypes().get(0));
        when(productTypeService.saveProductType(any(ProductTypeForm.class))).thenReturn(43);
        return productTypeService;
    }

    @Bean
    SupplierService supplierService() {
        SupplierService supplierService = mock(SupplierService.class);
        when(supplierService.getAllSuppliers()).thenReturn(TestData.suppliers());
        when(supplierService.getSupplierById(anyInt())).thenReturn(TestData.suppliers().get(0));
        when(supplierService.saveSupplier(any(SupplierForm.class))).thenReturn(44);
        return supplierService;
    }

    @Bean
    ProductIncomeService productIncomeService() {
        ProductIncomeService productIncomeService = mock(ProductIncomeService.class);
        when(productIncomeService.getProductIncomeById(anyInt())).thenReturn(TestData.productIncomes().get(0));
        when(productIncomeService.saveProductIncome(any(ProductIncomeForm.class))).thenReturn(45);
        return productIncomeService;
    }

}
