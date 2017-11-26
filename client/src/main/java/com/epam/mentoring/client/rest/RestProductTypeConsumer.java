package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.ProductTypeConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;

import java.util.List;

public class RestProductTypeConsumer implements ProductTypeConsumer{
    @Override
    public Integer saveProductType(ProductType productType) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProductType(ProductTypeForm productTypeForm) throws ServerDataAccessException {
        return null;
    }

    @Override
    public ProductType findProductType(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductType> findAll() throws ServerDataAccessException {
        return null;
    }
}
