package com.epam.mentoring.data.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.util.Assert;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;

public class ProductDaoImpl implements IProductDao{
	
	@Value("${product.get.by_id}")
	private String getProductByIdSql;
	
	@Value("${product.get.all}")
	private String getAllProductsSql;
	
	@Value("${product.add}")
	private String addProductSql;
	
	@Value("${product.update}")
	private String updateProductSql;
	
	@Value("${product.delete}")
	private String deleteProductSql;
	
	@Value("${product.quantity.get.by_id}")
	private String getProductQuantityBtIdSql;
	
	@Value("${product.quantity.get.all}")
	private String getAllProductsWithQuantitiesSql;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ProductRowMapper productRowMapper;
	
	@Autowired
	ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesMapper;
	
	public ProductDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product getProductById(int id) {
		Product product = jdbcTemplate.queryForObject(getProductByIdSql, new Object[] {id}, productRowMapper);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProduct(Product product) {
		Assert.notNull(product, "No product provided for saving");
		return jdbcTemplate.update(addProductSql, product.getProductName(), product.getPrice(), product.getType().getId());
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
		Map<Product, Integer> map = jdbcTemplate.query(getAllProductsWithQuantitiesSql, productsWithQuantitiesMapper);		
		return map;
	}


}
