package com.epam.mentoring.data.dao;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.data.model.Product;

public interface IProductDao {
	Product getProductById(int id);
	List<Product> getAllProducts();
	int getProductQuantity(int id);
	Map<Product,Integer> getAllProductsQuantities();
	int addProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(int id);
	

}
