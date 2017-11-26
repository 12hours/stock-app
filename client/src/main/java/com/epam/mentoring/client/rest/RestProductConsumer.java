package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;

import java.util.List;
import java.util.Map;

public class RestProductConsumer implements ProductConsumer{
    @Override
    public Product findProductById(Long id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Product findProductByName(String name) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<Product> findAllProductsByType(ProductType type) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProduct(Product product) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProduct(ProductForm productForm) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer getProductQuantity(Long id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantites() throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws ServerDataAccessException {
        return null;
    }

    @Override
    public void deleteProductById(Long id) throws ServerDataAccessException {

    }
}
