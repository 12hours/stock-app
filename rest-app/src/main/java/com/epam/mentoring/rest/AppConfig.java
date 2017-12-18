package com.epam.mentoring.rest;

import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {

    static {
        org.slf4j.MDC.put("app.name","rest-app");
    }

}
