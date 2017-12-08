package com.epam.mentoring.rest.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLDialectConfiguratorApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String H2 = "h2";
    private static final String MySQL = "mysql";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        List<String> activeProfilesList = Arrays.asList(activeProfiles);
        Collections.sort(activeProfilesList, String::compareToIgnoreCase);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        if (Collections.binarySearch(activeProfilesList, MySQL, String::compareToIgnoreCase) >= 0) {
//
//        } else {
//            // set h2 default
//            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//            InputStream ris = contextClassLoader.getResourceAsStream("h2-database-init.properties");
//            BufferedReader bfr = new BufferedReader(new InputStreamReader(ris));
//            try {
//                System.out.println("MSG:" + bfr.read());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();

    }
}
