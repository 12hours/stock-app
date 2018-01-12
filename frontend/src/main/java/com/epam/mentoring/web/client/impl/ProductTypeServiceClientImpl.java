package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.web.client.ProductTypeServiceClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProductTypeServiceClientImpl extends GenericCRUDRestClient implements ProductTypeServiceClient {

    @Value("${backend.host}"+"${backend.type.uri}")
    private String productTypeUri;

    public ProductTypeServiceClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate, objectMapper);
    }

    @Override
    public CollectionDTO<ProductTypeView> findAllProductTypes() {
        return getCollection(productTypeUri, new TypeReference<CollectionDTO<ProductTypeView>>() {});
    }

    @Override
    public Integer saveProductType(ProductTypeForm productTypeForm) {
        return saveObject(productTypeUri, productTypeForm);
    }
}
