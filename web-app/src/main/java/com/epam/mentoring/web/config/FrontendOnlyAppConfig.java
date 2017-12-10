package com.epam.mentoring.web.config;

import com.epam.mentoring.client.rest.RestProductConsumer;
import com.epam.mentoring.client.rest.RestProductIncomeConsumer;
import com.epam.mentoring.client.rest.RestProductTypeConsumer;
import com.epam.mentoring.client.rest.RestSupplierConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
@Configuration
@PropertySource("classpath:/application.properties")
public class FrontendOnlyAppConfig {

    @Autowired
    Environment env;

    @Bean
    Properties uriProperties() {
        Properties uriProperties = new Properties();

        String PROTOCOL = env.getProperty("backend.uri.protocol");
        String HOST = env.getProperty("backend.uri.host");
        String PORT = env.getProperty("backend.uri.port");
        String PREFIX = env.getProperty("backend.uri.prefix");
        String STOCK = env.getProperty("backend.uri.stock");
        String PRODUCT = env.getProperty("backend.uri.product");
        String INCOME = env.getProperty("backend.uri.income");
        String SUPPLIER = env.getProperty("backend.uri.supplier");
        String PRODUCT_TYPE = env.getProperty("backend.uri.product_type");

        String BACKEND_HOST = PROTOCOL + "://" + HOST + ":" + PORT + PREFIX;

        uriProperties.put("STOCK_URI", BACKEND_HOST + STOCK);
        uriProperties.put("PRODUCT_URI", BACKEND_HOST + PRODUCT);
        uriProperties.put("INCOME_URI", BACKEND_HOST + INCOME);
        uriProperties.put("SUPPLIER_URI", BACKEND_HOST + SUPPLIER);
        uriProperties.put("PRODUCT_TYPE_URI", BACKEND_HOST + PRODUCT_TYPE);
        log.info("Using Backend API URL: {}", BACKEND_HOST);
        return uriProperties;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    RestProductConsumer restProductConsumer() {
        return new RestProductConsumer(restTemplate(), uriProperties().getProperty("STOCK_URI"),
                uriProperties().getProperty("PRODUCT_URI"));
    }

    @Bean
    RestProductIncomeConsumer restProductIncomeConsumer() {
        return new RestProductIncomeConsumer(restTemplate(), uriProperties().getProperty("INCOME_URI"));
    }

    @Bean
    RestProductTypeConsumer restProductTypeConsumer() {
        return new RestProductTypeConsumer(restTemplate(), uriProperties().getProperty("PRODUCT_TYPE_URI"));
    }

    @Bean
    RestSupplierConsumer restSupplierConsumer() {
        return new RestSupplierConsumer(restTemplate(), uriProperties().getProperty("SUPPLIER_URI"));
    }

}
