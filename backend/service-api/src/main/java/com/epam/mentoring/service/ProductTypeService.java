package com.epam.mentoring.service;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

/**
 * Interface for ProductType service
 */
public interface ProductTypeService {
    ProductType getProductTypeById(Integer id) throws DataAccessException;

    List<ProductType> getAllProductTypes() throws DataAccessException;

    void updateProductType(ProductType productType) throws DataAccessException;

    Integer saveProductType(ProductType productType) throws DataAccessException;

    Integer saveProductType(ProductTypeForm productTypeForm) throws DataAccessException;

    void deleteProductType(Integer id) throws DataAccessException;

    Collection<Product> getAllProductsOfType(Integer id);

    void addProductToProductType(Integer id, Product product);
}
