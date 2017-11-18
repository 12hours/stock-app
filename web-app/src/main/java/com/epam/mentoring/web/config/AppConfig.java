package com.epam.mentoring.web.config;

import java.net.URL;
import java.net.URLClassLoader;

import javax.sql.DataSource;

import com.epam.mentoring.client.*;
import com.epam.mentoring.data.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.epam.mentoring.data.config.DaoConfig;
import com.epam.mentoring.service.IProductService;
import com.epam.mentoring.service.ProductService;

@Configuration
@Import(DaoConfig.class)
@PropertySource("classpath:/application.properties")
public class AppConfig {
	
	Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	IProductService productService() {
		return new ProductService(new ProductDaoImpl(dataSource));
	}

	@Bean
	IProductConsumer directProductConsumer() {
		return new DirectProductConsumer(productService());
	}

	@Bean
	IProductTypeConsumer directProductTypeConsumer() {
		return new DirectProductTypeConsumer(new ProductTypeDaoImpl(dataSource));
	}

	@Bean
	IProductIncomeConsumer directProductIncomeConsumer() {
		return new DirectProductIncomeConsumer(new ProductIncomeDaoImpl(dataSource));
	}

	@Bean
	ISupplierConsumer directSupplierConsumer() {
		return new DirectSupplierConsumer(new SupplierDaoImpl(dataSource));
	}
}
