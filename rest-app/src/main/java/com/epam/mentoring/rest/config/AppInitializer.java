package com.epam.mentoring.rest.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static org.springframework.web.context.ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected ApplicationContextInitializer<?>[] getServletApplicationContextInitializers() {
        return new ApplicationContextInitializer<?>[] {new SQLDialectConfiguratorApplicationContextInitializer()};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.setInitParameter("contextInitializerClasses", "com.epam.mentroing.rest.config" +
//                ".SQLDialectConfiguratorApplicationContextInitializer");

        System.out.println("STARTUP");
        String initializerClasses = servletContext.getInitParameter(CONTEXT_INITIALIZER_CLASSES_PARAM);

        String propertySourceInitClassName = SQLDialectConfiguratorApplicationContextInitializer.class.getName();

        if (StringUtils.hasText(initializerClasses)) {
            initializerClasses += " " + propertySourceInitClassName;
        }
        else {
            initializerClasses = propertySourceInitClassName;
        }
        servletContext.setInitParameter(CONTEXT_INITIALIZER_CLASSES_PARAM, initializerClasses);
        System.out.println(servletContext.getInitParameter(CONTEXT_INITIALIZER_CLASSES_PARAM));
        super.onStartup(servletContext);
    }

    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
        System.out.println(servletContext.getInitParameter(CONTEXT_INITIALIZER_CLASSES_PARAM));
    }


    @Override
    protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
        return new ApplicationContextInitializer<?>[] {new SQLDialectConfiguratorApplicationContextInitializer()};
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                RestWebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
