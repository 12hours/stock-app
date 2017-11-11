package stock.app.data.config;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfig {
	
	private static final String JDBC_URL = "jdbc:h2:mem:test";
	private static final String JDBC_DRIVER = "org.h2.Driver";
	
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(JDBC_DRIVER);
		dataSource.setUrl(JDBC_URL);
		return dataSource;
	}
}
