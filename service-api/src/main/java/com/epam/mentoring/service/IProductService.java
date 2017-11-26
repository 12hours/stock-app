package com.epam.mentoring.service;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

/**
 * Interface for Product service
 */
public interface IProductService {
	
	Product findProductById(Integer id) throws DataAccessException;
	
	Product findProductByName(String name) throws DataAccessException;
	
	List<Product> findAllProductsByType(ProductType type) throws DataAccessException;
	
	Integer saveProduct(Product product) throws DataAccessException;

    Integer saveProduct(ProductForm productForm) throws DataAccessException;

    Product updateProduct(Long id, Product product) throws DataAccessException;
	
	List<Product> getAllProducts() throws DataAccessException;
	
	Integer getProductQuantity(Long id) throws DataAccessException;
	
	Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException;
	
	void deleteProductById(Long id) throws DataAccessException;

    List<ProductWithQuantityView> getAllProductsWithQuantitiesViews();
}
