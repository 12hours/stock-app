package com.epam.mentoring.data.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContextLoadedListener implements ApplicationListener<ContextRefreshedEvent> {

    Logger log = LoggerFactory.getLogger(ContextLoadedListener.class);

    @Autowired
    ConfigurableApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // debug purposes
        log.debug("Application context created");
        for (String name : context.getBeanDefinitionNames()) {
            log.debug("Bean created: " + name);
        }
        for (String profile : context.getEnvironment().getActiveProfiles()) {
            System.out.println("Found profile "+profile);
        }

        System.out.println("spring.profiles.active= "+context.getEnvironment().getProperty("spring.profiles.active"));
    }
}
