package com.epam.mentoring.client;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

public interface IProductConsumer {
	
	public Product findProductById(Long id) throws ServerDataAccessException;
	
	public Product findProductByName(String name) throws ServerDataAccessException;
	
	public List<Product> findAllProductsByType(ProductType type) throws ServerDataAccessException;
	
	public Product saveProduct(Product product) throws ServerDataAccessException;
	
	public Product updateProduct(Long id, Product product) throws ServerDataAccessException;
	
	public List<Product> getAllProducts() throws ServerDataAccessException;
	
	public Integer getProductQuantity(Long id) throws ServerDataAccessException;
	
	public Map<Product, Integer> getAllProductsWithQuantites() throws ServerDataAccessException;
	
	public void deleteProductById(Long id) throws ServerDataAccessException;
	
}
