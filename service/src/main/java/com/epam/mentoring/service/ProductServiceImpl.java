package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ProductDao;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class.getName());
	
	private ProductDao productDao;
	
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public Product findProductById(Integer id) throws DataAccessException {
		Assert.notNull(id, "No id provided");
		logger.debug("Getting product with id: " + id);
		try {
			Product product = productDao.getProductById(id);
			return product;
		} catch (EmptyResultDataAccessException e) {
			logger.debug("No object found");
			return null;
		}
	}
	
	@Override
	public Integer saveProduct(Product product) throws DataAccessException {
		Assert.notNull(product, "No product provided");
		Assert.notNull(product.getName(), "No product name provided");
		Assert.notNull(product.getPrice(), "No product price provided");
		Assert.notNull(product.getType().getId(), "No product type id provided");
		logger.debug("Saving product " + product.toString());
		return productDao.addProduct(product);
	}

	@Override
	public Integer saveProduct(ProductForm productForm) throws DataAccessException {
		Product product = DTOUtils.map(productForm, Product.class);
		return saveProduct(product);
	}

    @Override
    public void updateProduct(Product product) throws DataAccessException {
        Assert.notNull(product, "No product provided");
        Assert.notNull(product.getId(), "No product id provided");
        Assert.notNull(product.getName(), "No product name provided");
        Assert.notNull(product.getPrice(), "No product price provided");
        Assert.notNull(product.getType().getId(), "No product type id provided");
        logger.debug("Updating product with id: {}", product.getId());
        productDao.updateProduct(product);
    }

    @Override
    public void updateProduct(ProductForm productForm) throws DataAccessException {
        Product product = DTOUtils.map(productForm, Product.class);
        updateProduct(product);
    }

    @Override
	public List<Product> getAllProducts() throws DataAccessException {
		List<Product> products = productDao.getAllProducts();
		logger.debug("Getting all products. Found " + products.size() + " items");
		return products;
	}

	public List<ProductView> getAllProductsAsViews() throws DataAccessException {
		List<Product> productsList = getAllProducts();
		List<ProductView> productViewsList = new ArrayList<>();
		productsList.forEach(product -> productViewsList.add(DTOUtils.map(product, ProductView.class)));
		return productViewsList;
	}
	
	@Override
	public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException{
		Map<Product, Integer> productsWithQuantitiesMap = productDao.getAllProductsWithQuantities();
		logger.debug("getting products with quantities: " + productsWithQuantitiesMap.size() + " items found");
		return productsWithQuantitiesMap;
	}
	
	@Override
	public void deleteProductById(Integer id) throws DataAccessException {
		Assert.notNull(id, "No id provided");
		logger.debug("Deleting item with id: {}", id);
		productDao.deleteProduct(id);
	}

	@Override
	public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() {
		List<ProductWithQuantityView> allProductsWithQuantitesAsViews = productDao.getAllProductsWithQuantitesAsViews();
		logger.debug("getting all products with quantities as views: " + allProductsWithQuantitesAsViews.size() + " " +
				"items found");
		return allProductsWithQuantitesAsViews;
	}

}
