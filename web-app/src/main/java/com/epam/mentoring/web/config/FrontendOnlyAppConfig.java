package com.epam.mentoring.web.config;

import com.epam.mentoring.client.rest.RestProductConsumer;
import com.epam.mentoring.client.rest.RestProductIncomeConsumer;
import com.epam.mentoring.client.rest.RestProductTypeConsumer;
import com.epam.mentoring.client.rest.RestSupplierConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
@PropertySource("classpath:/application.properties")
public class FrontendOnlyAppConfig {

    @Autowired
    Environment env;

    Properties uriProperties;

    private Properties uriProperties() {
        if (this.uriProperties != null) return uriProperties;

        Properties extraProperties = new Properties();
        String BACKEND_HOST = env.getProperty("backend.uri.protocol") + "://"
                + env.getProperty("backend.uri.host") + ":"
                + env.getProperty("backend.uri.port")
                + env.getProperty("backend.uri.prefix");

        extraProperties.put("STOCK_URI", BACKEND_HOST + env.getProperty("backend.uri.stock"));
        extraProperties.put("PRODUCT_URI", BACKEND_HOST + env.getProperty("backend.uri.product"));
        extraProperties.put("INCOME_URI", BACKEND_HOST + env.getProperty("backend.uri.income"));
        extraProperties.put("SUPPLIER_URI", BACKEND_HOST + env.getProperty("backend.uri.supplier"));
        extraProperties.put("PRODUCT_TYPE_URI", BACKEND_HOST + env.getProperty("backend.uri.product_type"));
        this.uriProperties = extraProperties;
        return extraProperties;
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
