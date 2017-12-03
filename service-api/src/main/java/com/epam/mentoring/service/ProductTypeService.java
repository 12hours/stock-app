package com.epam.mentoring.service;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface for ProductType service
 */
public interface ProductTypeService {
    ProductType getProductTypeById(Integer id) throws DataAccessException;
    List<ProductType> getAllProductTypes() throws DataAccessException;
    Integer updateProductType(ProductType productType) throws DataAccessException;
    Integer saveProductType(ProductType productType) throws DataAccessException;

    Integer saveProductType(ProductTypeForm productTypeForm) throws DataAccessException;

    int deleteProductType(Integer id) throws DataAccessException;
}
