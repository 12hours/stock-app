package com.epam.mentoring;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.web.client.ProductIncomeServiceClient;
import com.epam.mentoring.web.client.ProductServiceClient;
import com.epam.mentoring.web.client.ProductTypeServiceClient;
import com.epam.mentoring.web.client.SupplierServiceClient;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Profile("test")
public class TestConfig {

    @Value("${backend.host}" + "${backend.product.uri}")
    private String productUri;

    @Value("${backend.host}" + "${backend.type.uri}")
    private String productTypeUri;

    @Value("${backend.host}" + "${backend.supplier.uri}")
    private String supplierUri;

    @Value("${backend.host}" + "${backend.income.uri}")
    private String productIncomeUri;

    @Bean
    RestTemplate restTemplateMock() {
        RestTemplate restTemplate = mock(RestTemplate.class);

        // ProductWithQuanitity
        ResponseEntity productWithQuantitesResponseEntity = mock(ResponseEntity.class);
        when(productWithQuantitesResponseEntity.getBody()).thenReturn(TestJsonData.productWithQuantitiesResponseJson);
        when(restTemplate.getForEntity(productUri + "/quantities", String.class)).thenReturn(productWithQuantitesResponseEntity);

        // Product
        ResponseEntity productSavedResponseEntity = mock(ResponseEntity.class);
        when(productSavedResponseEntity.getBody()).thenReturn(TestJsonData.productSavedJson);
        when(restTemplate.postForEntity(eq(productUri), any(ProductForm.class), any(Class.class))).thenReturn(productSavedResponseEntity);

        ResponseEntity allProductsResponseEntity = mock(ResponseEntity.class);
        when(allProductsResponseEntity.getBody()).thenReturn(TestJsonData.productsJson);
        when(restTemplate.getForEntity(productUri, String.class)).thenReturn(allProductsResponseEntity);

        // ProductType
        ResponseEntity productTypeSavedResponseEntity = mock(ResponseEntity.class);
        when(productTypeSavedResponseEntity.getBody()).thenReturn(TestJsonData.productTypeSavedJson);
        when(restTemplate.postForEntity(eq(productTypeUri), any(ProductTypeForm.class), any(Class.class))).thenReturn(productTypeSavedResponseEntity);

        ResponseEntity allProductTypesResponseEntity = mock(ResponseEntity.class);
        when(allProductTypesResponseEntity.getBody()).thenReturn(TestJsonData.productTypesJson);
        when(restTemplate.getForEntity(productTypeUri, String.class)).thenReturn(allProductTypesResponseEntity);

        // Supplier
        ResponseEntity supplierSavedResponseEntity = mock(ResponseEntity.class);
        when(supplierSavedResponseEntity.getBody()).thenReturn(TestJsonData.supplierSavedJson);
        when(restTemplate.postForEntity(eq(supplierUri), any(SupplierForm.class), any(Class.class))).thenReturn(supplierSavedResponseEntity);

        ResponseEntity allSuppliersResponseEntity = mock(ResponseEntity.class);
        when(allSuppliersResponseEntity.getBody()).thenReturn(TestJsonData.suppliersJson);
        when(restTemplate.getForEntity(supplierUri, String.class)).thenReturn(allSuppliersResponseEntity);

        // ProductIncome
        ResponseEntity productIncomeSavedResponseEntity = mock(ResponseEntity.class);
        when(productIncomeSavedResponseEntity.getBody()).thenReturn(TestJsonData.productIncomeSavedJson);
        when(restTemplate.postForEntity(eq(productIncomeUri), any(ProductIncomeForm.class), any(Class.class))).thenReturn(productIncomeSavedResponseEntity);


        return restTemplate;
    }

    @Bean
    @Profile("controllerTest")
    ProductServiceClient productServiceClient() {
        ProductServiceClient productServiceClient = mock(ProductServiceClient.class);
        when(productServiceClient.findAllProductsWithQuantities()).thenReturn(TestObjectData.productWithQuantityViewCollectionDTO());
        when(productServiceClient.findAllProducts()).thenReturn(TestObjectData.productViewCollectionDTO());
        when(productServiceClient.saveProduct(any(ProductForm.class))).thenReturn(Integer.valueOf(12));
        when(productServiceClient.findProductById(any(Integer.class))).thenReturn(
                TestObjectData.productViewCollectionDTO().getItems().iterator().next());
        return productServiceClient;
    }

    @Bean
    @Profile("controllerTest")
    ProductTypeServiceClient productTypeServiceClient() {
        ProductTypeServiceClient productTypeServiceClient = mock(ProductTypeServiceClient.class);
        when(productTypeServiceClient.findAllProductTypes()).thenReturn(TestObjectData.productTypeViewCollectionDTO());
        when(productTypeServiceClient.saveProductType(any(ProductTypeForm.class))).thenReturn(Integer.valueOf(5));
        return productTypeServiceClient;
    }

    @Bean
    @Profile("controllerTest")
    ProductIncomeServiceClient productIncomeServiceClient() {
        ProductIncomeServiceClient productIncomeServiceClient = mock(ProductIncomeServiceClient.class);
        when(productIncomeServiceClient.saveProductIncome(any(ProductIncomeForm.class))).thenReturn(Integer.valueOf(6));
        return productIncomeServiceClient;
    }

    @Bean
    @Profile("controllerTest")
    SupplierServiceClient supplierServiceClient() {
        SupplierServiceClient supplierServiceClient = mock(SupplierServiceClient.class);
        when(supplierServiceClient.findAllSuppliers()).thenReturn(TestObjectData.supplierViewCollectionDTO());
        when(supplierServiceClient.saveSupplier(any(SupplierForm.class))).thenReturn(Integer.valueOf(7));
        return supplierServiceClient;
    }

}
