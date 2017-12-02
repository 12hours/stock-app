package com.epam.mentoring.rest.config;

import com.epam.mentoring.data.config.DaoConfig;
import com.epam.mentoring.data.dao.*;
import com.epam.mentoring.service.*;
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

    @Bean
    IProductTypeDao productTypeDao() {
        return new ProductTypeDaoImpl(dataSource);
    }

    @Bean
    IProductTypeService productTypeService() {
        return new ProductTypeService(productTypeDao());
    }

    @Bean
    ISupplierDao supllierDao() {
        return new SupplierDaoImpl(dataSource);
    }

    @Bean
    ISupplierService supplierService() {
        return new SupplierService(supllierDao());
    }
}
