package com.epam.mentoring.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContextLoadedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ConfigurableApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Context active: "+context.isActive());
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
