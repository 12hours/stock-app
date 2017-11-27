package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.service.*;
import com.epam.mentoring.test.TestData;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@ComponentScan(basePackageClasses = {StockController.class})
@EnableWebMvc
public class TestConfig {

    @Bean
    List<ProductWithQuantityView> refProductWithQuantityViewList() {
        return new ArrayList<ProductWithQuantityView>() {
            {
                add(new ProductWithQuantityView(0, "Core i7", 15));
                add(new ProductWithQuantityView(1, "Core i5", 20));
                add(new ProductWithQuantityView(2, "Core i3", 25));
            }
        };
    }

    @Bean
    IProductService productService() {
        IProductService productService = mock(IProductService.class);
        when(productService.getAllProductsWithQuantitiesViews()).thenReturn(refProductWithQuantityViewList());
        return productService;
    }


    @Bean
    IProductIncomeService productIncomeService() {
        IProductIncomeService productIncomeService = mock(IProductIncomeService.class);
        when(productIncomeService.saveProductIncome(any(ProductIncomeForm.class))).thenAnswer((Answer<Integer>) invocation -> {
            ProductIncomeForm form = invocation.getArgumentAt(0, ProductIncomeForm.class);
            if (form.getProductId() == 42) {
                throw new DataAccessException("Can not save"){

                };
            } else {
                return 10;
            }
        });
        return productIncomeService;
    }

    @Bean
    IProductTypeService productTypeService(){
        IProductTypeService productTypeServiceMock = mock(IProductTypeService.class);
        when(productTypeServiceMock.getAllProductTypes()).thenReturn(TestData.productTypes());
        when(productTypeServiceMock.saveProductType(any(ProductType.class))).thenReturn(10);
        when(productTypeServiceMock.saveProductType(any(ProductTypeForm.class))).thenReturn(10);
        return productTypeServiceMock;
    }

    @Bean
    ISupplierService supplierService() {
        SupplierService supplierServiceMock = mock(SupplierService.class);
        when(supplierServiceMock.getAllSuppliers()).thenReturn(TestData.suppliers());
        when(supplierServiceMock.saveSupplier(any(Supplier.class))).thenReturn(10);
        when(supplierServiceMock.saveSupplier(any(SupplierForm.class))).thenReturn(10);
        return supplierServiceMock;
    }

}
