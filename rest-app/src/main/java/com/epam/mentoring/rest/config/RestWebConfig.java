package com.epam.mentoring.rest.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.epam.mentoring.rest.controllers.StockController;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@ComponentScan(basePackageClasses = {StockController.class})
@EnableWebMvc
public class RestWebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public org.springframework.validation.Validator getValidator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setProviderClass(HibernateValidator.class);
//        validator.setValidationMessageSource(messageSource());
//        return validator;
//    }

    private MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }
}
