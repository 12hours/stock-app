package com.epam.mentoring.data.config;

import com.epam.mentoring.data.dao.*;
import com.epam.mentoring.data.util.mappers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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

//@Conditional(H2DatabaseCondition.class)
@Configuration
@PropertySource({"classpath:/h2-database-sql.properties", "classpath:/h2-database-init.properties"})
@ComponentScan(basePackages = {"com.epam.mentoring.data.util.mappers"})
public class H2DatabaseConfig {

    Logger log = LoggerFactory.getLogger(H2DatabaseConfig.class);

    @Bean(name = "H2InMemoryDataSource")
//	@Profile({"test"})
    DataSource dataSource() {
        try {
            Class.forName(org.h2.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
//                .addScripts(new String[] {"h2/create-tables.sql", "h2/data.sql"})
                .build();
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        configurer.setIgnoreUnresolvablePlaceholders(true);
//        return configurer;
//    }

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
//	    populator.addScript(dataScript);
        return populator;
    }

    @Bean
    ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesResultSetExtractor() {
        return new ProductsWithQuantitiesResultSetExtractor();
    }

    @Bean
    ProductRowMapper productRowMapper() {
        return new ProductRowMapper();
    }

    @Bean
    ProductResultSetExtractor productResultSetExtractor() {
        return new ProductResultSetExtractor();
    }

    @Bean
    SupplierRowMapper supplerRowMapper() {
        return new SupplierRowMapper();
    }

    @Bean
    SuppliersResultSetExtractor suppliersResultSetExtractor() {
        return new SuppliersResultSetExtractor();
    }

    @Bean
    ProductTypeRowMapper productTypeRowMapper() {
        return new ProductTypeRowMapper();
    }

    @Bean
    ProductTypesResultSetExtractor productTypesResultSetExtractor() {
        return new ProductTypesResultSetExtractor();
    }

    @Bean
    ProductIncomeRowMapper productIncomeRowMapper() {
        return new ProductIncomeRowMapper();
    }

    @Bean
    ProductIncomesResultSetExtractor productIncomesResultSetExtractor() {
        return new ProductIncomesResultSetExtractor();
    }


    @Bean(name = "productDaoImpl")
    ProductDao productDaoImpl() {
        log.info("ProductDaoImpl bean created");
        ProductDao dao = new ProductDaoImpl(dataSource());
        return dao;
    }

    @Bean(name = "supplierDaoImpl")
    SupplierDao supplierDaoImpl() {
        SupplierDao dao = new SupplierDaoImpl(dataSource());
        return dao;
    }

    @Bean(name = "productTypeDaoImpl")
    ProductTypeDao productTypeDaoImpl() {
        ProductTypeDao dao = new ProductTypeDaoImpl(dataSource());
        return dao;
    }

    @Bean(name = "productIncomeDaoImpl")
    ProductIncomeDao productIncomeDaoImpl() {
        ProductIncomeDao dao = new ProductIncomeDaoImpl(dataSource());
        return dao;
    }

}
