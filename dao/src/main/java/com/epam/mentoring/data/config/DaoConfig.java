package com.epam.mentoring.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;
import com.epam.mentoring.data.util.mappers.SupplierRowMapper;

@Configuration
@ComponentScan(basePackages = {"com.epam.mentoring.data.util.mappers", "com.epam.mentoring.data.config"})
public class DaoConfig {

}
