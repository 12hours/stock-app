package com.epam.mentoring.client;

import java.util.List;
import java.util.Map;


import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;

public interface ProductConsumer {
	
	Product findProductById(Long id) throws ServerDataAccessException;
	
	Product findProductByName(String name) throws ServerDataAccessException;
	
	List<Product> findAllProductsByType(ProductType type) throws ServerDataAccessException;
	
	Integer saveProduct(Product product) throws ServerDataAccessException;

	Integer saveProduct(ProductForm productForm) throws ServerDataAccessException;
	
	Product updateProduct(Long id, Product product) throws ServerDataAccessException;
	
	List<Product> getAllProducts() throws ServerDataAccessException;

    List<ProductView> getAllProductViews() throws ServerDataAccessException;

    Integer getProductQuantity(Long id) throws ServerDataAccessException;
	
	Map<Product, Integer> getAllProductsWithQuantites() throws ServerDataAccessException;

	List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws ServerDataAccessException;
	
	void deleteProductById(Long id) throws ServerDataAccessException;
	
}
