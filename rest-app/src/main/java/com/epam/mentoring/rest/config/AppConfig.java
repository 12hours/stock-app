package com.epam.mentoring.rest.config;

import com.epam.mentoring.data.config.DaoConfig;
import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.dao.ProductDaoImpl;
import com.epam.mentoring.data.dao.ProductIncomeDaoImpl;
import com.epam.mentoring.service.IProductIncomeService;
import com.epam.mentoring.service.IProductService;
import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(DaoConfig.class)
public class AppConfig {

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
    IProductIncomeDao productIncomeDao() {
        return new ProductIncomeDaoImpl(dataSource);
    }

    @Bean
    IProductIncomeService productIncomeService() {
        return new ProductIncomeService(productIncomeDao());
    }
}
