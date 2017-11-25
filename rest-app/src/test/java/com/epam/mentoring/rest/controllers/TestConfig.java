package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.IProductIncomeService;
import com.epam.mentoring.service.IProductService;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import java.util.*;

import static org.mockito.Matchers.any;
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
//        when(productIncomeService.saveProductIncome(Matchers.argThat(
//                new ArgumentMatcher<ProductIncomeForm>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        ProductIncomeForm form = (ProductIncomeForm) item;
////                        return true;
//                        return form.getProductId() == 42;
//                    }
//                }))).thenThrow(DataAccessException.class);
//        when(productIncomeService.saveProductIncome(any(ProductIncomeForm.class))).thenReturn(10);
        when(productIncomeService.saveProductIncome(any(ProductIncomeForm.class))).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                ProductIncomeForm form = invocation.getArgumentAt(0, ProductIncomeForm.class);
                if (form.getProductId() == 42) {
                    throw new DataAccessException("Can not save"){

                    };
                } else {
                    return 10;
                }
            }
        });
        return productIncomeService;
    }


}
