package com.epam.mentoring.service;

import com.epam.mentoring.data.model.ProductType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IProductTypeService {
    ProductType getProductTypeById(Integer id) throws DataAccessException;
    List<ProductType> getAllProductTypes() throws DataAccessException;
    int updateProductType(ProductType productType) throws DataAccessException;
    int saveProductType(ProductType productType) throws DataAccessException;
    int deleteProductType(Integer id) throws DataAccessException;
}
