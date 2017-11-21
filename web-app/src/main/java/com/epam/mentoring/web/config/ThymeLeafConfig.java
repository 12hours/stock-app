package com.epam.mentoring.web.config;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymeLeafConfig {

	@Bean
	@Description("Thymeleaf Template Resolver")
	ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver  templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}
	
	@Bean
	@Description("Thymeleaf Template Engine")
	SpringTemplateEngine templateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(templateResolver());
		return springTemplateEngine;
	}
	
	@Bean
	@Description("Thymeleaf View Resolver")
	ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine((SpringTemplateEngine) templateEngine());
		thymeleafViewResolver.setOrder(1);
//		thymeleafViewResolver.setViewNames(new String[]{"*.html"});
		return thymeleafViewResolver;
	}
	
	@Bean
	@Description("Spring Message Resolver")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}

	@Bean("productIncomeForm")
	public ProductIncomeForm productIncomeForm() {
		return new ProductIncomeForm();
	}

}
