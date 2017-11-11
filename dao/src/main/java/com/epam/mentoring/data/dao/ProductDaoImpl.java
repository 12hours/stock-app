package com.epam.mentoring.data.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesMapper;

public class ProductDaoImpl implements IProductDao{
	
	@Value("product.get.by_id")
	private String getProductByIdQuery;
	
	@Value("product.get.all")
	private String getAllProductsQuery;
	
	@Value("product.add")
	private String addProductQuery;
	
	@Value("product.update")
	private String updateProductQuery;
	
	@Value("product.delete")
	private String deleteProductQuery;
	
	@Value("product.quantity.get.by_id")
	private String getProductQuantityBtIdQuery;
	
	@Value("product.quantity.get.all")
	private String getAllProductsWithQuantitiesQuery;
	
	private JdbcTemplate jdbcTemplate;
	
	public ProductDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getProductQuantity(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Product, Integer> getAllProductsWithQuantities() {
		Map<Product, Integer> map = jdbcTemplate.queryForObject(getAllProductsWithQuantitiesQuery, new ProductsWithQuantitiesMapper());
		return map;
	}


}
