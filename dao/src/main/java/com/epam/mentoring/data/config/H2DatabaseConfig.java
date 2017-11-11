package com.epam.mentoring.data.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.epam.mentoring.data.config.conditions.H2DatabaseCondition;

@Conditional(H2DatabaseCondition.class)
@Configuration
@PropertySource("classpath:/h2-database-sql.properties")
public class H2DatabaseConfig {
	
}
