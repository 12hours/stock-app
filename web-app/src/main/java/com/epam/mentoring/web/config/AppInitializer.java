package com.epam.mentoring.web.config;

import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	Logger logger = LoggerFactory.getLogger(AppInitializer.class);

	@Override
	protected ApplicationContextInitializer<?>[] getServletApplicationContextInitializers() {
		ApplicationContextInitializer[] applicationContextInitializers = new ApplicationContextInitializer[]{
				new TomcatJNDIPropertiesLoaderApplicationContextInitializer()
		};
		return applicationContextInitializers;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
//		return new Class[] {
//
//			};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
			WebConfig.class,
			ThymeLeafConfig.class,
			FrontendOnlyAppConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }
	

}
