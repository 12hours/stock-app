package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.ProductTypeConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
public class RestProductTypeConsumer implements ProductTypeConsumer{

    private String PRODUCT_TYPE_URI;

    private RestTemplate restTemplate;

    public RestProductTypeConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        PRODUCT_TYPE_URI = "http://localhost:8080/product-type";
    }

    public RestProductTypeConsumer(RestTemplate restTemplate, String uri) {
        this.PRODUCT_TYPE_URI = uri;
        this.restTemplate = restTemplate;
    }

    @Override
    public Integer saveProductType(ProductType productType) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProductType(ProductTypeForm productTypeForm) throws ServerDataAccessException {
        log.debug("Saving product type form: {}", productTypeForm.toString());
        try {
            URI uri = restTemplate.postForLocation(PRODUCT_TYPE_URI, productTypeForm);
            log.debug("Saving success");
        } catch (RestClientException e) {
            log.error("Can not save product type form: {}", e);
            throw new ServerDataAccessException("Can not save product type form", e);
        }
        return null;
    }

    @Override
    public ProductType findProductType(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductType> findAll() throws ServerDataAccessException {
        log.debug("Getting product types");
        try {
            ResponseEntity<List<ProductType>> responseEntity = restTemplate.exchange(PRODUCT_TYPE_URI, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<ProductType>>(){});
            List<ProductType> productTypes = responseEntity.getBody();
            return productTypes;
        } catch (RestClientException e) {
            log.error("Can not get product types: {}", e);
            throw new ServerDataAccessException("Can not get product types", e);
        }
    }
}
