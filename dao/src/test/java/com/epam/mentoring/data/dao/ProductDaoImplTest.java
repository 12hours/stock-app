package com.epam.mentoring.data.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.Product;

@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/h2-database-sql.properties")
public class ProductDaoImplTest {

	@Autowired
	@Qualifier("ProductDaoImpl")
	private IProductDao dao;
	
	@Test
	@Sql({"classpath:/create_tables.sql"})
	public void getAllProductsWithQuantitesTest() {
//		Map<Product, Integer> productsAndQuantitesMap = dao.getAllProductsWithQuantities();
		
	}
	
	
	
}
