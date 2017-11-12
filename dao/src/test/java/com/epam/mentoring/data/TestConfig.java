package com.epam.mentoring.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.dao.ProductDaoImpl;
import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;

@Configuration
@Profile("test")
@PropertySource("classpath:/h2-database-sql.properties")
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
	ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesMapper() {
		return new ProductsWithQuantitiesResultSetExtractor();
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
	
    @Bean
    JdbcTemplate jdbcTemplate() {
    	return new JdbcTemplate(dataSource());
    }

	
}
