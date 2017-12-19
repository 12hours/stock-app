package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.ProductTypeService;
import com.epam.mentoring.test.TestData;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    ProductTypeService productTypeService() {
        ProductTypeService productTypeService = Mockito.mock(ProductTypeService.class);
        Mockito.when(productTypeService.getAllProductTypes()).thenReturn(TestData.productTypes());
        Mockito.when(productTypeService.getProductTypeById(Mockito.anyInt())).thenReturn(TestData.productTypes().get(0));
        Mockito.when(productTypeService.saveProductType(Mockito.any(ProductTypeForm.class))).thenReturn(43);
        return productTypeService;
    }

}
