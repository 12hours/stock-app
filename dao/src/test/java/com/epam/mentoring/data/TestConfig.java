package com.epam.mentoring.data;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.dao.IProductTypeDao;
import com.epam.mentoring.data.dao.ISupplierDao;
import com.epam.mentoring.data.dao.ProductDaoImpl;
import com.epam.mentoring.data.dao.ProductIncomeDaoImpl;
import com.epam.mentoring.data.dao.ProductTypeDaoImpl;
import com.epam.mentoring.data.dao.SupplierDaoImpl;
import com.epam.mentoring.data.util.mappers.ProductIncomeRowMapper;
import com.epam.mentoring.data.util.mappers.ProductIncomesResultSetExtractor;
import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductTypeRowMapper;
import com.epam.mentoring.data.util.mappers.ProductTypesResultSetExtractor;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;
import com.epam.mentoring.data.util.mappers.SupplierRowMapper;
import com.epam.mentoring.data.util.mappers.SuppliersResultSetExtractor;


@Configuration
@Profile("test")
@PropertySource("classpath:/h2/h2-database-sql.properties")
public class TestConfig {
//
	@Bean
	DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUrl("jdbc:h2:mem:test");
//		return dataSource;
		return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
                .build();
	}
	
	@Bean
	ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesResultSetExtractor() {
		return new ProductsWithQuantitiesResultSetExtractor();
	}
	
	@Bean
	ProductRowMapper productRowMapper() {
		return new ProductRowMapper();
	}
	
	@Bean
	SupplierRowMapper supplerRowMapper() {
		return new SupplierRowMapper();
	}
	
	@Bean
	SuppliersResultSetExtractor suppliersResultSetExtractor() {
		return new SuppliersResultSetExtractor();
	}
	
	@Bean
	ProductTypeRowMapper productTypeRowMapper() {
		return new ProductTypeRowMapper();
	}
	
	@Bean
	ProductTypesResultSetExtractor productTypesResultSetExtractor() {
		return new ProductTypesResultSetExtractor();
	}
	
	@Bean
	ProductIncomeRowMapper productIncomeRowMapper() {
		return new ProductIncomeRowMapper();
	}
	
	@Bean
	ProductIncomesResultSetExtractor productIncomesResultSetExtractor() {
		return new ProductIncomesResultSetExtractor();
	}
	
	
	@Bean(name = "ProductDaoImpl")
	IProductDao productDao() {
		IProductDao dao = new ProductDaoImpl(dataSource());
		return dao;
	}
	
	@Bean
	ISupplierDao supplierDao() {
		ISupplierDao dao = new SupplierDaoImpl(dataSource());
		return dao;
	}
	
	@Bean
	IProductTypeDao productTypeDao() {
		IProductTypeDao dao = new ProductTypeDaoImpl(dataSource());
		return dao;
	}
	
	@Bean
	IProductIncomeDao productIncomeDao() {
		IProductIncomeDao dao = new ProductIncomeDaoImpl(dataSource());
		return dao;
	}
	
	
	
    @Bean
    JdbcTemplate jdbcTemplate() {
    	return new JdbcTemplate(dataSource());
    }

	
}
