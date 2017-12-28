package com.epam.mentoring.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

public class PreConfig {

    @Autowired
    ConfigurableEnvironment environment;

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean(name="envConfig")
    @Lazy(false)
    ApplicationListener configure() {
        environment.getPropertySources().addFirst(new PropertiesPropertySource("database.profile", new Properties(){
            {
                put("database.profile", "h2");
            }
        }));
        System.out.println("Ordered");
        return new ContextLoadedListener();
    }

}
