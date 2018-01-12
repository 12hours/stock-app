package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.web.client.ProductIncomeServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductIncomeServiceClientImpl extends GenericCRUDRestClient implements ProductIncomeServiceClient {

    @Value("${backend.host}"+"${backend.income.uri}")
    private String productIncomeUri;

    public ProductIncomeServiceClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate, objectMapper);
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) {
        return saveObject(productIncomeUri, productIncomeForm);
    }
}
