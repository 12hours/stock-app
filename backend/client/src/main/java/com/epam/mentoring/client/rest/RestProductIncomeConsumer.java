package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.ProductIncomeConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class RestProductIncomeConsumer implements ProductIncomeConsumer {

    private RestTemplate restTemplate;

    private String productIncomeLocation;

    public RestProductIncomeConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        productIncomeLocation = "http://localhost:8080/income";
    }

    public RestProductIncomeConsumer(RestTemplate restTemplate, String uri) {
        this.restTemplate = restTemplate;
        this.productIncomeLocation = uri;
    }

    @Override
    public Integer saveProductIncome(ProductIncome productIncome) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws ServerDataAccessException {
        log.debug("Saving product income form: {}", productIncomeForm);
        try {
            restTemplate.postForLocation(productIncomeLocation, productIncomeForm);
        } catch (RestClientException ex) {
            log.error("Can not save product income form: {}", ex);
            throw new ServerDataAccessException("Can not save product income form", ex);
        }
        return null;
    }

    @Override
    public ProductIncome findProductIncome(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductIncome> findAll() throws ServerDataAccessException {
        return null;
    }
}
