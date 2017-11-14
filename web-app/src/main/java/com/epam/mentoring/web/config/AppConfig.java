package com.epam.mentoring.web.config;

import java.net.URL;
import java.net.URLClassLoader;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.epam.mentoring.client.DirectProductConsumer;
import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.data.config.DaoConfig;
import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.dao.ProductDaoImpl;
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
	
}
