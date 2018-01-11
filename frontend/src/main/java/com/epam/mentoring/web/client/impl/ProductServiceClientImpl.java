package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.web.client.ProductServiceClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProductServiceClientImpl extends GenericCRUDRestClient implements ProductServiceClient {

    @Value("${backend.host}"+"${backend.product.uri}")
    private String productUri;

    public ProductServiceClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate, objectMapper);
    }

    @Override
    public ProductView findProductById(Integer id) {
        return null;
    }

    @Override
    public CollectionDTO<ProductView> findAllProducts() {
        return getCollection(productUri, new TypeReference<CollectionDTO<ProductView>>() {});
    }

    @Override
    public CollectionDTO<ProductWithQuantityView> findAllProductsWithQuantities() {
        String productWithQuantitesUrl = productUri + "/quantities";
        return getCollection(productWithQuantitesUrl, new TypeReference<CollectionDTO<ProductWithQuantityView>>() {});
    }

    @Override
    public Integer saveProduct(ProductForm productForm) {
        return saveObject(productUri, productForm);
    }
}
