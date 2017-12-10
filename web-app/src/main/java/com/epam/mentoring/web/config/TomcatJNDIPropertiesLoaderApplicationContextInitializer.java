package com.epam.mentoring.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class TomcatJNDIPropertiesLoaderApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Properties jndiProperites = new Properties();
        try {
            Context initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            String jndiProperitesLocation = (String) envCtx.lookup("uriConfiguration");
            jndiProperites.load(new FileInputStream(jndiProperitesLocation));
        } catch (Exception e) {
            log.warn("No uri configuration provided by container. Starting with inner properties");
//            e.printStackTrace();
        }
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new PropertySource<String>("Tomcat JNDI properties") {
            @Override
            public Object getProperty(String name) {
                return jndiProperites.getProperty(name);
            }
        });
    }
}
