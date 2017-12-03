package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.*;
import com.epam.mentoring.service.*;
import com.epam.mentoring.test.TestData;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
    ProductIncomeService productIncomeService() {
        ProductIncomeService productIncomeService = mock(ProductIncomeService.class);
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
    ProductTypeService productTypeService(){
        ProductTypeService productTypeServiceMock = mock(ProductTypeService.class);
        when(productTypeServiceMock.getAllProductTypes()).thenReturn(TestData.productTypes());
        when(productTypeServiceMock.saveProductType(any(ProductType.class))).thenReturn(10);
        when(productTypeServiceMock.saveProductType(any(ProductTypeForm.class))).thenReturn(10);
        return productTypeServiceMock;
    }

    @Bean
    SupplierService supplierService() {
        SupplierServiceImpl supplierServiceImplMock = mock(SupplierServiceImpl.class);
        when(supplierServiceImplMock.getAllSuppliers()).thenReturn(TestData.suppliers());
        when(supplierServiceImplMock.saveSupplier(any(Supplier.class))).thenReturn(10);
        when(supplierServiceImplMock.saveSupplier(any(SupplierForm.class))).thenReturn(10);
        return supplierServiceImplMock;
    }

    @Bean
    ProductService productService() {
        ProductService productServiceMock = mock(ProductServiceImpl.class);
        when(productServiceMock.getAllProductsWithQuantitiesViews()).thenReturn(refProductWithQuantityViewList());
        when(productServiceMock.getAllProductsAsViews()).thenCallRealMethod();
        when(productServiceMock.getAllProducts()).thenReturn(TestData.products());
        when(productServiceMock.saveProduct(any(Product.class))).thenReturn(10);
        when(productServiceMock.saveProduct(any(ProductForm.class))).thenReturn(10);
        return productServiceMock;
    }

}
