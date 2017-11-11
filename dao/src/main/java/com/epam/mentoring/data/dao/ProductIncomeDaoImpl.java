package com.epam.mentoring.data.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.mentoring.data.model.ProductIncome;

public class ProductIncomeDaoImpl implements IProductIncomeDao{
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Value("productIncome.get.byId")
	private String getProductIncomeByIdQuery;
	
	public ProductIncomeDaoImpl(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public ProductIncome getProductIncomeById(int id) {
		
		return null;
	}

	@Override
	public void addProductIncome(ProductIncome productIncome) {
		// TODO Auto-generated method stub
		
	}

}
