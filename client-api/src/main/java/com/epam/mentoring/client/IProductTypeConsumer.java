package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductType;

import java.util.List;

public interface IProductTypeConsumer {

    Integer saveProductType(ProductType productType) throws ServerDataAccessException;

    ProductType findProductType(Integer id) throws ServerDataAccessException;

    List<ProductType> findAll() throws ServerDataAccessException;
}
