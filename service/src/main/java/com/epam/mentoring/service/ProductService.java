package com.epam.mentoring.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

public class ProductService implements IProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class.getName());
	
	private IProductDao productDao;
	
	public ProductService(IProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public Product findProductById(Long id) {
		return null;
	}
	
	@Override
	public Product findProductByName(String name) {
		return null;
	}
	
	@Override
	public List<Product> findAllProductsByType(ProductType type){
		return null;
	}
	
	@Override
	public Product saveProduct(Product product) {
		return null;
	}
	
	@Override
	public Product updateProduct(Long id, Product product) {
		return null;
	}
	
	@Override
	public List<Product> getAllProducts(){
		return null;
	}
	
	@Override
	public Integer getProductQuantity(Long id) {
		return null;
	}
	
	@Override
	public Map<Product, Integer> getAllProductsWithQuantites(){
		Map<Product, Integer> productsWithQuantitiesMap = productDao.getAllProductsWithQuantities();
		logger.debug("getting products with quantities: " + productsWithQuantitiesMap.size() + " items found");
		return productsWithQuantitiesMap;
	}
	
	@Override
	public void deleteProductById(Long id) {
		
	}

	@Override
	public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
