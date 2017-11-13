package com.epam.mentoring.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

public interface IProductService {
	
	public Product findProductById(Long id) throws DataAccessException;
	
	public Product findProductByName(String name) throws DataAccessException;
	
	public List<Product> findAllProductsByType(ProductType type) throws DataAccessException;
	
	public Product saveProduct(Product product) throws DataAccessException;
	
	public Product updateProduct(Long id, Product product) throws DataAccessException;
	
	public List<Product> getAllProducts() throws DataAccessException;
	
	public Integer getProductQuantity(Long id) throws DataAccessException;
	
	public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException;
	
	public void deleteProductById(Long id) throws DataAccessException;
	
}
