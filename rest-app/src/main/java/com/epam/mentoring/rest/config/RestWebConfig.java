package com.epam.mentoring.rest.config;

import com.epam.mentoring.data.config.DaoConfig;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import com.epam.mentoring.rest.controllers.StockController;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
@ComponentScan(basePackageClasses = {StockController.class})
@EnableWebMvc
@PropertySource("classpath:application.properties")
//@Import(DaoConfig.class)
public class RestWebConfig extends WebMvcConfigurerAdapter {

    private MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }
}
