package com.epam.mentoring.client;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.service.IProductService;

public class DirectProductConsumer implements IProductConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(DirectProductConsumer.class.getName());
	
	private IProductService productService;
	
	public DirectProductConsumer(IProductService productService) {
		this.productService = productService;
	}
	
	public Product findProductById(Long id) {
		return null;
	}
	
	public Product findProductByName(String name) {
		return null;
	}
	
	public List<Product> findAllProductsByType(ProductType type){
		return null;
	}
	
	public Product saveProduct(Product product) {
		return null;
	}
	
	public Product updateProduct(Long id, Product product) {
		return null;
	}
	
	public List<Product> getAllProducts(){
		return null;
	}
	
	public Integer getProductQuantity(Long id) {
		return null;
	}
	
	public Map<Product, Integer> getAllProductsWithQuantites(){
		Map<Product, Integer> productsWithQuantitiesMap = productService.getAllProductsWithQuantities();
		logger.debug("getting products with quantities: " + productsWithQuantitiesMap.size() + " items found");
		return productsWithQuantitiesMap;
	}
	
	public void deleteProductById(Long id) {
		
	}
	
}
