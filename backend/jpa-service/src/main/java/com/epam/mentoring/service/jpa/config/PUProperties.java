package com.epam.mentoring.service.jpa.config;

import java.util.Properties;

public class PUProperties {
    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/test_db");
        properties.put("javax.persistence.jdbc.user", "root");
        properties.put("javax.persistence.jdbc.password", "root");
        return properties;
    }
}
