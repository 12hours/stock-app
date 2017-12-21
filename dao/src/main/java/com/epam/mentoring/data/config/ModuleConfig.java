package com.epam.mentoring.data.config;

import com.epam.mentoring.data.config.datasource.DataSourceConfig;
import com.epam.mentoring.data.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Configuration
@Import(DataSourceConfig.class)
@ComponentScan(basePackages = {"com.epam.mentoring.data.util.mappers"})
public class ModuleConfig {

    Logger log = LoggerFactory.getLogger(ModuleConfig.class);

    @Autowired
    DataSource dataSource;

    @Value("${database.create.tables}")
    private String schemaScript;

    @Value("${database.populate.tables}")
    private String dataScript;

    @Value("${database.init")
    private String databaseInit;

    @Value("${database.clean.tables")
    private String databaseClean;

    @Value("${database.delete.tables")
    private String databaseDrop;

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
            if (databaseClean.equalsIgnoreCase("true")) {
                populator.addScript(new InputStreamResource(new ByteArrayInputStream(dataScript.getBytes(StandardCharsets.UTF_8.name()))));
            }
            if (databaseDrop.equalsIgnoreCase("true")) {
                populator.addScript(new InputStreamResource(new ByteArrayInputStream(dataScript.getBytes(StandardCharsets.UTF_8.name()))));
            }
            populator.addScript(new InputStreamResource(new ByteArrayInputStream(schemaScript.getBytes(StandardCharsets.UTF_8.name()))));
            if (databaseInit.equalsIgnoreCase("true")) {
                populator.addScript(new InputStreamResource(new ByteArrayInputStream(dataScript.getBytes(StandardCharsets.UTF_8.name()))));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return populator;
    }

    @Bean(name = "productDaoImpl")
    ProductDao productDaoImpl() {
        log.debug("ProductDaoImpl bean created");
        ProductDao dao = new ProductDaoImpl(dataSource);
        return dao;
    }

    @Bean(name = "supplierDaoImpl")
    SupplierDao supplierDaoImpl() {
        log.debug("SupplierDaoImpl bean created");
        SupplierDao dao = new SupplierDaoImpl(dataSource);
        return dao;
    }

    @Bean(name = "productTypeDaoImpl")
    ProductTypeDao productTypeDaoImpl() {
        log.debug("ProductTypeDaoImpl bean created");
        ProductTypeDao dao = new ProductTypeDaoImpl(dataSource);
        return dao;
    }

    @Bean(name = "productIncomeDaoImpl")
    ProductIncomeDao productIncomeDaoImpl() {
        log.debug("ProductIncomeDaoImpl bean created");
        ProductIncomeDao dao = new ProductIncomeDaoImpl(dataSource);
        return dao;
    }

}
