package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.IProductIncomeService;
import com.epam.mentoring.service.IProductService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
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
        return productIncomeService;
    }

}
