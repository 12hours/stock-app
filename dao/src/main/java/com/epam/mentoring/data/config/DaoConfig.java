package com.epam.mentoring.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;

@Configuration
@ComponentScan
public class DaoConfig {
	
	@Bean
	ProductRowMapper productRowMapper() {
		return new ProductRowMapper();
	}
	
	@Bean
	ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesResultSetExtractor() {
		return new ProductsWithQuantitiesResultSetExtractor();
	}
}
