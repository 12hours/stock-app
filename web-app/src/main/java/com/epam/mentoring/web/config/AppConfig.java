package com.epam.mentoring.web.config;

import javax.sql.DataSource;

import com.epam.mentoring.client.*;
import com.epam.mentoring.client.direct.DirectProductConsumer;
import com.epam.mentoring.client.direct.DirectProductIncomeConsumer;
import com.epam.mentoring.client.direct.DirectProductTypeConsumer;
import com.epam.mentoring.client.direct.DirectSupplierConsumer;
import com.epam.mentoring.data.dao.*;
import com.epam.mentoring.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.epam.mentoring.data.config.DaoConfig;

@Configuration
@Import(DaoConfig.class)
@PropertySource("classpath:/application.properties")
public class AppConfig {

	Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Autowired
	DataSource dataSource;
	
	@Bean
    ProductDao productDao() {
		return new ProductDaoImpl(dataSource);
	}

	@Bean
	ProductService productService() {
		return new ProductServiceImpl(productDao());
	}

	@Bean
    ProductConsumer directProductConsumer() {
		return new DirectProductConsumer(productService());
	}

	@Bean
	ProductTypeDao productTypeDao(){
		return new ProductTypeDaoImpl(dataSource);
	}

	@Bean
	ProductTypeService productTypeService() {
		return new ProductTypeServiceImpl(productTypeDao());
	}

	@Bean
    ProductTypeConsumer directProductTypeConsumer() {
		return new DirectProductTypeConsumer(productTypeService());
	}

	@Bean
	ProductIncomeDao productIncomeDao() {
		return new ProductIncomeDaoImpl(dataSource);
	}

	@Bean
	ProductIncomeService productIncomeService() {
		return new ProductIncomeServiceImp(productIncomeDao());
	}

	@Bean
    ProductIncomeConsumer directProductIncomeConsumer() {
		return new DirectProductIncomeConsumer(productIncomeService());
	}

	@Bean
	SupplierDao supplierDao() {
		return new SupplierDaoImpl(dataSource);
	}

	@Bean
	SupplierService supplierService() {
		return new SupplierServiceImpl(supplierDao());
	}

	@Bean
    SupplierConsumer directSupplierConsumer() {
		return new DirectSupplierConsumer(supplierService());
	}
}
