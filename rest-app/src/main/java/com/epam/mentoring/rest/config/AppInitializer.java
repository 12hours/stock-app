package com.epam.mentoring.rest.config;

import com.epam.mentoring.data.config.DaoConfig;
import com.epam.mentoring.rest.filters.RequestLogFilter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Override
//    protected ApplicationContextInitializer<?>[] getServletApplicationContextInitializers() {
//        return new ApplicationContextInitializer<?>[] {new SQLDialectConfiguratorApplicationContextInitializer()};
//    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {

        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                DataSourceConfig.class,
                RestWebConfig.class,
                AppConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/", "/api" };
    }

    @Override
    protected Filter[] getServletFilters() {
        //encoding filter
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        //logging filter
        Filter requestLoggingFilter = new RequestLogFilter();
        return new Filter[] { requestLoggingFilter, encodingFilter };
    }
}
