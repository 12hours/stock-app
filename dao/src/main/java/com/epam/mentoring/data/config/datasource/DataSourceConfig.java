package com.epam.mentoring.data.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${database.type}")
    private String databaseType;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.name}")
    private String databaseName;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @Autowired
    Environment env;

    @Bean(name = "H2InMemoryDataSource")
    DataSource dataSource() {

        switch (databaseType) {
            case ("h2"):
                return createH2MemDataSource();
            case ("mysql"):
                return createMySqlDataSource();
            default:
                return null;
        }

    }

    private DataSource createMySqlDataSource() {
        try {
            Class.forName(com.mysql.jdbc.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            log.error("Mysql driver not found: {}", e);
        }

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl("jdbc:mysql://" + databaseUrl + "/" + databaseName);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);
        try {
            dataSource.setDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/"));
        } catch (SQLException e) {
            log.error("Driver manager can not locate driver");
            e.printStackTrace();
        }

        log.debug("mysql datasource created");
        return dataSource;
    }


    private DataSource createH2MemDataSource() {
        try {
            Class.forName(org.h2.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            log.error("H2 Driver not found: {}", e);
        }
        DataSource datasource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName(databaseName)
                .build();
        log.debug("H2 in-memory datasource created");
        return datasource;
    }


}
