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
	IProductDao productDao() {
		return new ProductDaoImpl(dataSource);
	}

	@Bean
	IProductService productService() {
		return new ProductService(productDao());
	}

	@Bean
	IProductConsumer directProductConsumer() {
		return new DirectProductConsumer(productService());
	}

	@Bean
	IProductTypeDao productTypeDao(){
		return new ProductTypeDaoImpl(dataSource);
	}

	@Bean
	IProductTypeConsumer directProductTypeConsumer() {
		return new DirectProductTypeConsumer(productTypeDao());
	}

	@Bean
	IProductIncomeDao productIncomeDao() {
		return new ProductIncomeDaoImpl(dataSource);
	}

	@Bean
	IProductIncomeConsumer directProductIncomeConsumer() {
		return new DirectProductIncomeConsumer(productIncomeDao());
	}

	@Bean
	ISupplierDao supplierDao() {
		return new SupplierDaoImpl(dataSource);
	}

	@Bean
	ISupplierConsumer directSupplierConsumer() {
		return new DirectSupplierConsumer(supplierDao());
	}
}
