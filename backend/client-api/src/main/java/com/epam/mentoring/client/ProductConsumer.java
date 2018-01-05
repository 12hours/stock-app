package com.epam.mentoring.client;

import java.util.List;
import java.util.Map;


import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;

public interface ProductConsumer {
	
	Product findProductById(Long id) throws ServerDataAccessException;
	
	Integer saveProduct(Product product) throws ServerDataAccessException;

	Integer saveProduct(ProductForm productForm) throws ServerDataAccessException;
	
	List<Product> getAllProducts() throws ServerDataAccessException;

    List<ProductView> getAllProductViews() throws ServerDataAccessException;

	Map<Product, Integer> getAllProductsWithQuantites() throws ServerDataAccessException;

	List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws ServerDataAccessException;

	void updateProduct(Integer id, Product product) throws ServerDataAccessException;

	void deleteProductById(Integer id) throws ServerDataAccessException;

}
