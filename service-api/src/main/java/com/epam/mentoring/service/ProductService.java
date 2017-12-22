package com.epam.mentoring.service;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

/**
 * Interface for Product service
 */
public interface ProductService {
	
	Product findProductById(Integer id) throws DataAccessException;
	
	Integer saveProduct(Product product) throws DataAccessException;

    Integer saveProduct(ProductForm productForm) throws DataAccessException;

    void updateProduct(Product product) throws DataAccessException;

    void updateProduct(ProductForm product) throws DataAccessException;

	List<Product> getAllProducts() throws DataAccessException;

	Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException;
	
	void deleteProductById(Integer id) throws DataAccessException;

    List<ProductWithQuantityView> getAllProductsWithQuantitiesViews();
}
