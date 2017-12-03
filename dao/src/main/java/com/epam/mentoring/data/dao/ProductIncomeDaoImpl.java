package com.epam.mentoring.data.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.util.mappers.ProductIncomeRowMapper;

import java.util.List;

public class ProductIncomeDaoImpl implements ProductIncomeDao {

	private JdbcTemplate jdbcTemplate;

	@Value("${product_income.get.by_id}")
	private String GET_PRODUCT_INCOME_BY_ID_SQL;
	
	@Value("${product_income.add}")
	private String ADD_PRODUCT_INCOME_SQL;
	
	@Autowired
	private ProductIncomeRowMapper productIncomeRowMapper;

	public ProductIncomeDaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
		return jdbcTemplate.queryForObject(GET_PRODUCT_INCOME_BY_ID_SQL, new Object[] {id}, productIncomeRowMapper);
	}

	@Override
	public Integer addProductIncome(ProductIncome productIncome) throws DataAccessException {
		return jdbcTemplate.update(ADD_PRODUCT_INCOME_SQL, productIncome.getDate(), productIncome.getOrderNumber(),
				productIncome.getQuantity(), productIncome.getProduct().getId(), productIncome.getSupplier().getId(),
				productIncome.getUser().getId());
	}

	@Override
	public Integer deleteProductIncome(Integer id) throws DataAccessException {
		return null;
	}

	@Override
	public Integer updateProductIncome(ProductIncome productIncome) throws DataAccessException {
		return null;
	}

	@Override
	public List<ProductIncome> getAllProductIncomes() throws DataAccessException {
		return null;
	}

}
