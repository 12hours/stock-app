package com.epam.mentoring.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.epam.mentoring.rest.controllers.StockController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@ComponentScan(basePackageClasses = {StockController.class})
@EnableWebMvc
public class RestWebConfig {
}
