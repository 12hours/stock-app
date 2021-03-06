package com.epam.mentoring.data.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.util.mappers.ProductTypeRowMapper;
import com.epam.mentoring.data.util.mappers.ProductTypesResultSetExtractor;

public class ProductTypeDaoImpl implements ProductTypeDao {
	
	@Value("${product_type.get.by_id}")
	private String GET_PRODUCT_TYPE_BY_ID_SQL;

	@Value("${product_type.get.all}")
	private String GET_ALL_PRODUCT_TYPES_SQL;

	@Value("${product_type.add}")
	private String ADD_PRODUCT_TYPE_SQL;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductTypeRowMapper productTypeRowMapper;

	@Autowired
	private ProductTypesResultSetExtractor productTypeResultSetExtractor;
	
	public ProductTypeDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ProductType getProductTypeById(Integer id) throws DataAccessException {
		ProductType productType = jdbcTemplate.queryForObject(GET_PRODUCT_TYPE_BY_ID_SQL, new Object[] {id}, productTypeRowMapper);
		return productType;
	}

	@Override
	public List<ProductType> getAllProductTypes() throws DataAccessException {
		List<ProductType> productTypes = jdbcTemplate.query(GET_ALL_PRODUCT_TYPES_SQL, productTypeResultSetExtractor);
		return productTypes;
	}

	@Override
	public int updateProductType(ProductType productType) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addProductType(ProductType productType) throws DataAccessException {
		return jdbcTemplate.update(ADD_PRODUCT_TYPE_SQL, productType.getName());
	}

	@Override
	public int deleteProductType(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
