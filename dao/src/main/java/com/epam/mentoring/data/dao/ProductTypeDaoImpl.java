package com.epam.mentoring.data.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.util.mappers.ProductTypeRowMapper;
import com.epam.mentoring.data.util.mappers.ProductTypesResultSetExtractor;

public class ProductTypeDaoImpl implements IProductTypeDao {
	
	@Value("${product_type.get.by_id}")
	private String getProductTypeById;
	
	@Value("${product_type.get.all}")
	private String getAllProductTypes;
	
	@Value("${product_type.add}")
	private String addProductType;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductTypeRowMapper productTypeRowMapper;

	@Autowired
	private ProductTypesResultSetExtractor productTypeResultSetExtractor;
	
	public ProductTypeDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ProductType getProductTypeById(int id) throws DataAccessException {
		ProductType productType = jdbcTemplate.queryForObject(getProductTypeById, new Object[] {id}, productTypeRowMapper);
		return productType;
	}

	@Override
	public List<ProductType> getAllProductTypes() throws DataAccessException {
		List<ProductType> productTypes = jdbcTemplate.query(getAllProductTypes, productTypeResultSetExtractor);
		return productTypes;
	}

	@Override
	public int updateProductType(ProductType productType) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addProductType(ProductType productType) throws DataAccessException {
		Assert.notNull(productType, "No productType provided for saving");
		return jdbcTemplate.update(addProductType, productType.getTypeName());
	}

	@Override
	public int deleteProductType(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
