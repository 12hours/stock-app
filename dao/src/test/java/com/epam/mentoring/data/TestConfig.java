package com.epam.mentoring.data;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.dao.ProductDaoImpl;
import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesMapper;

@Configuration
public class TestConfig {

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:test");
		return dataSource;
	}
	
	@Bean
	ProductsWithQuantitiesMapper productsWithQuantitiesMapper() {
		return new ProductsWithQuantitiesMapper();
	}
	
	@Bean
	ProductRowMapper productRowMapper() {
		return new ProductRowMapper();
	}
	
	
	@Bean(name = "ProductDaoImpl")
	IProductDao productDao() {
		IProductDao dao = new ProductDaoImpl(dataSource());
		return dao;
	}
	
}
