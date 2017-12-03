package com.epam.mentoring.web.config;

import com.epam.mentoring.client.rest.RestProductConsumer;
import com.epam.mentoring.client.rest.RestProductIncomeConsumer;
import com.epam.mentoring.client.rest.RestProductTypeConsumer;
import com.epam.mentoring.client.rest.RestSupplierConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:/application.properties")
public class FrontendOnlyAppConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    RestProductConsumer restProductConsumer() {
        return new RestProductConsumer(restTemplate());
    }

    @Bean
    RestProductIncomeConsumer restProductIncomeConsumer() {
        return new RestProductIncomeConsumer(restTemplate());
    }

    @Bean
    RestProductTypeConsumer restProductTypeConsumer() {
        return new RestProductTypeConsumer(restTemplate());
    }

    @Bean
    RestSupplierConsumer restSupplierConsumer() {
        return new RestSupplierConsumer(restTemplate());
    }

}
