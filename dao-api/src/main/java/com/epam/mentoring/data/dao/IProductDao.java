package com.epam.mentoring.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Product;

public interface IProductDao {
	Product getProductById(int id) throws DataAccessException;
	List<Product> getAllProducts() throws DataAccessException;
	int getProductQuantity(int id) throws DataAccessException;
	Map<Product,Integer> getAllProductsWithQuantities() throws DataAccessException;
	int addProduct(Product product) throws DataAccessException;
	int updateProduct(Product product) throws DataAccessException;
	int deleteProduct(int id) throws DataAccessException;
	

}
