package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.SupplierConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
public class RestSupplierConsumer implements SupplierConsumer {

    private RestTemplate restTemplate;

    private String SUPPLIER_URI;

    public RestSupplierConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        SUPPLIER_URI = "http://localhost:8080/supplier";
    }

    public RestSupplierConsumer(RestTemplate restTemplate, String uri) {
        this.restTemplate = restTemplate;
        this.SUPPLIER_URI = uri;
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) throws ServerDataAccessException {
        log.debug("Saving supplier form: {}", supplierForm.toString());
        try {
            URI uri = restTemplate.postForLocation(SUPPLIER_URI, supplierForm);
            // TODO: this method (and other similiar client classes methods) has to return something!!!
            return null;
        } catch (RestClientException ex) {
            log.error("Can not save supplier form: {}", ex);
            throw new ServerDataAccessException("Can not save supplier form", ex);
        }
    }

    @Override
    public Supplier findSupplier(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<Supplier> findAll() throws ServerDataAccessException {
        log.debug("Getting all suppliers");

        try {
            ResponseEntity<List<Supplier>> responseEntity = restTemplate.exchange(SUPPLIER_URI, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Supplier>>() {
                    });
            // TODO: add response status code checkig to client classes
            //responseEntity.getStatusCode()
            List<Supplier> suppliers = responseEntity.getBody();
            return suppliers;
        } catch (RestClientException ex) {
            log.error("Can not get suppliers: {}", ex);
            throw new ServerDataAccessException("Can not get suppliers", ex);
        }
    }
}
