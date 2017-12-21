package com.epam.mentoring.data.config;

import com.epam.mentoring.data.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan(basePackages = {"com.epam.mentoring.data.util.mappers"})
public class H2DatabaseConfig {

    Logger log = LoggerFactory.getLogger(H2DatabaseConfig.class);

    @Bean(name = "H2InMemoryDataSource")
    DataSource dataSource() {
        try {
            Class.forName(org.h2.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            log.error("H2 Driver not found: {}", e);
        }
        DataSource datasource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
                .build();
        log.debug("H2 in-memory datasource created");
        return datasource;
    }

    @Value("${database.create.tables}")
    private String schemaScript;

    @Value("${database.populate.tables}")
    private String dataScript;

    @Bean
    @Autowired
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        try {
            populator.addScript(new InputStreamResource(new ByteArrayInputStream(schemaScript.getBytes(StandardCharsets.UTF_8.name()))));
            populator.addScript(new InputStreamResource(new ByteArrayInputStream(dataScript.getBytes(StandardCharsets.UTF_8.name()))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return populator;
    }

    @Bean(name = "productDaoImpl")
    ProductDao productDaoImpl() {
        log.debug("ProductDaoImpl bean created");
        ProductDao dao = new ProductDaoImpl(dataSource());
        return dao;
    }

    @Bean(name = "supplierDaoImpl")
    SupplierDao supplierDaoImpl() {
        log.debug("SupplierDaoImpl bean created");
        SupplierDao dao = new SupplierDaoImpl(dataSource());
        return dao;
    }

    @Bean(name = "productTypeDaoImpl")
    ProductTypeDao productTypeDaoImpl() {
        log.debug("ProductTypeDaoImpl bean created");
        ProductTypeDao dao = new ProductTypeDaoImpl(dataSource());
        return dao;
    }

    @Bean(name = "productIncomeDaoImpl")
    ProductIncomeDao productIncomeDaoImpl() {
        log.debug("ProductIncomeDaoImpl bean created");
        ProductIncomeDao dao = new ProductIncomeDaoImpl(dataSource());
        return dao;
    }

}
