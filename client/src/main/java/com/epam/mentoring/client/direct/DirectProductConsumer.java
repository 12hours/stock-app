package com.epam.mentoring.client.direct;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.service.IProductService;

public class DirectProductConsumer implements ProductConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(DirectProductConsumer.class.getName());
	
	private IProductService productService;
	
	public DirectProductConsumer(IProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public Map<Product, Integer> getAllProductsWithQuantites() throws ServerDataAccessException {
		Map<Product, Integer> productsWithQuantitiesMap = productService.getAllProductsWithQuantities();
//		logger.debug("getting products with quantities: " + productsWithQuantitiesMap.size() + " items found");
		return productsWithQuantitiesMap;
	}

	@Override
	public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws ServerDataAccessException {
		return null;
	}

	@Override
	public void deleteProductById(Long id) {
		
	}

	@Override
	public Product findProductById(Long id) throws ServerDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductByName(String name) throws ServerDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllProductsByType(ProductType type) throws ServerDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveProduct(Product product) throws ServerDataAccessException {
		return productService.saveProduct(product);
	}

	@Override
	public Integer saveProduct(ProductForm productForm) throws ServerDataAccessException {
		return null;
	}

	@Override
	public Product updateProduct(Long id, Product product) throws ServerDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() throws ServerDataAccessException {
		return productService.getAllProducts();
	}

	@Override
	public List<ProductView> getAllProductViews() throws ServerDataAccessException {
		return null;
	}

	@Override
	public Integer getProductQuantity(Long id) throws ServerDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
