package com.epam.mentoring.data.config;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.epam.mentoring.data.config.conditions.H2DatabaseCondition;

@Conditional(H2DatabaseCondition.class)
@Configuration
@PropertySource("classpath:/h2-database-sql.properties")
@PropertySource("classpath:/h2-database-init.properties")
public class H2DatabaseConfig {
	
	
	@Bean(name = "H2InMemoryDataSource")
	@Profile({"dev"})
	DataSource devDataSource() {
		ResourceLoader resourceLoader = new ClassRelativeResourceLoader(H2DatabaseConfig.class);
		return new EmbeddedDatabaseBuilder(resourceLoader)
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
//                .addScripts(new String[] {"/h2/create-tables.sql", "/h2/data.sql"})
                .build();
	}
	
	@Bean(name = "H2InMemoryDataSource")
	@Profile({"test"})
	DataSource testDataSource() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
//                .addScripts(new String[] {"h2/create-tables.sql", "h2/data.sql"})
                .build();
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
//	    populator.addScript(dataScript);
	    return populator;
	}

}
