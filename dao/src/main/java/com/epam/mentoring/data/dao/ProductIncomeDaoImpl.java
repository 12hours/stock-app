package com.epam.mentoring.data.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.util.mappers.ProductIncomeRowMapper;

public class ProductIncomeDaoImpl implements IProductIncomeDao{
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Value("${product_income.get.by_id}")
	private String getProductIncomeByIdQuery;
	
	@Value("${product_income.add}")
	private String addProductIncome;
	
	@Autowired
	private ProductIncomeRowMapper productIncomeRowMapper;
	
	public ProductIncomeDaoImpl(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public ProductIncome getProductIncomeById(int id) throws DataAccessException {
		return jdbcTemplate.queryForObject(getProductIncomeByIdQuery, new Object[] {id}, productIncomeRowMapper);
	}

	@Override
	public int addProductIncome(ProductIncome productIncome) throws DataAccessException {
		Assert.notNull(productIncome, "No productIncome provided for saving");
		return jdbcTemplate.update(addProductIncome, productIncome.getDate(), productIncome.getOrderNumber(),
				productIncome.getQuantity(), productIncome.getProduct().getId(), productIncome.getSupplier().getId(),
				productIncome.getUser().getId());
	}

}
