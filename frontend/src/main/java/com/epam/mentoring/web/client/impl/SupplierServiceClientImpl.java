package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.data.model.dto.view.SupplierView;
import com.epam.mentoring.web.client.SupplierServiceClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SupplierServiceClientImpl extends GenericCRUDRestClient implements SupplierServiceClient{

    @Value("${backend.host}"+"${backend.supplier.uri}")
    private String supplierUri;

    public SupplierServiceClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate, objectMapper);
    }

    @Override
    public CollectionDTO<SupplierView> findAllSuppliers() {
        return getCollection(supplierUri, new TypeReference<CollectionDTO<SupplierView>>(){});
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) {
        return saveObject(supplierUri, supplierForm);
    }
}
