package com.epam.mentoring.rest2;


import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.ProductTypeService;
import com.epam.mentoring.service.SupplierService;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class AppConfig {

    @Bean
    public DataSource dataSource() {
        return null;
    }

    @Bean
    public ProductService productService() {
        return null;
    }

    @Bean
    public ProductIncomeService productIncomeService() {
        return null;
    }

    @Bean
    public ProductTypeService productTypeService() {
        return null;
    }

    @Bean
    public SupplierService supplierService() {
        return null;
    }
}
