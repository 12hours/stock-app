package com.epam.mentoring.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import org.springframework.util.Assert;

public class ProductService implements IProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class.getName());
	
	private IProductDao productDao;
	
	public ProductService(IProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public Product findProductById(Integer id) throws DataAccessException {
		return null;
	}
	
	@Override
	public Product findProductByName(String name) throws DataAccessException {
		return null;
	}
	
	@Override
	public List<Product> findAllProductsByType(ProductType type) throws DataAccessException {
		return null;
	}
	
	@Override
	public int saveProduct(Product product) throws DataAccessException {
		Assert.notNull(product, "No product provided");
		logger.debug("Saving product " + product.toString());
		return productDao.addProduct(product);
	}
	
	@Override
	public Product updateProduct(Long id, Product product) throws DataAccessException {
		return null;
	}
	
	@Override
	public List<Product> getAllProducts() throws DataAccessException {
		List<Product> products = productDao.getAllProducts();
		logger.debug("Getting all products. Found " + products.size() + " items");
		return products;
	}
	
	@Override
	public Integer getProductQuantity(Long id) throws DataAccessException {
		return null;
	}
	
	@Override
	public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException{
		Map<Product, Integer> productsWithQuantitiesMap = productDao.getAllProductsWithQuantities();
		logger.debug("getting products with quantities: " + productsWithQuantitiesMap.size() + " items found");
		return productsWithQuantitiesMap;
	}
	
	@Override
	public void deleteProductById(Long id) throws DataAccessException {
		
	}
	
}
