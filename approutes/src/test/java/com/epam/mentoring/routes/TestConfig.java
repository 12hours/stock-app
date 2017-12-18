package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.test.TestData;
import org.apache.camel.model.ModelCamelContext;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class TestConfig {

    @Bean
    ProductService productService() {
        ProductService productService = Mockito.mock(ProductService.class);
        Mockito.when(productService.getAllProducts()).thenReturn(TestData.products());
        Mockito.when(productService.findProductById(Mockito.anyInt())).thenReturn(TestData.products().get(0));
        Mockito.when(productService.saveProduct(Mockito.any(ProductForm.class))).thenReturn(42);
        return productService;
    }

}
