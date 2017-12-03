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
    ProductDao productDao() {
        return new ProductDaoImpl(dataSource);
    }

    @Bean
    ProductService productService() {
        return new ProductServiceImpl(productDao());
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
    ProductTypeDao productTypeDao() {
        return new ProductTypeDaoImpl(dataSource);
    }

    @Bean
    ProductTypeService productTypeService() {
        return new ProductTypeServiceImp(productTypeDao());
    }

    @Bean
    SupplierDao supllierDao() {
        return new SupplierDaoImpl(dataSource);
    }

    @Bean
    SupplierService supplierService() {
        return new SupplierServiceImpl(supllierDao());
    }
}
