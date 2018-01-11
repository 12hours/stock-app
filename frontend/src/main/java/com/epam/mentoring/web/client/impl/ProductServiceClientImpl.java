package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.web.client.ProductServiceClient;
import com.epam.mentoring.web.client.exception.ServerDataAccessException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
public class ProductServiceClientImpl implements ProductServiceClient {

    private RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${backend.host}"+"${backend.product.uri}")
    private String productUri;

    public ProductServiceClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ProductView findProductById(Integer id) {
        return null;
    }

    @Override
    public Collection<ProductView> findAllProducts() {
        return null;
    }

    @Override
    public CollectionDTO<ProductWithQuantityView> findAllProductsWithQuantities() {
        log.debug("Getting all products with quantities as views");
        String productWithQuantitesUrl = productUri + "/quantities";
        CollectionDTO<ProductWithQuantityView> dto = null;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(productWithQuantitesUrl, String.class);
            try {
                dto = objectMapper.readValue(response.getBody(), new TypeReference<CollectionDTO<ProductWithQuantityView>>() {});
            } catch (IOException e) {
                log.error("Can't get data from server: {}", e);
                throw new ServerDataAccessException("Can not recognize data returned by server", e);
            }
            return dto;
        } catch (RestClientException ex) {
            log.error("Can't get data from server: {}", ex);
            throw new ServerDataAccessException("Can't get data from server " + productWithQuantitesUrl, ex);
        }
    }

    @Override
    public Integer saveProduct(ProductForm productForm) {
        ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(productUri, productForm, Map.class);
        Map body = mapResponseEntity.getBody();
        return null;
    }
}
