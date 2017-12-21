package com.epam.mentoring.data.dao;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.ProductType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductTypeDaoImplTest {

	@Autowired
	private ProductTypeDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	DataSource dataSource;

	@Before
	public void setUp() throws SQLException {
		Resource resource = new ClassPathResource("create_tables.sql");
		Resource resource2 = new ClassPathResource("data.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(resource);
		databasePopulator.addScript(resource2);
		databasePopulator.populate(dataSource.getConnection());
	}


	@After
	public void cleanUp() throws SQLException {
		Resource resource = new ClassPathResource("delete_tables.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(resource);
		databasePopulator.populate(dataSource.getConnection());
	}

	@Test
//	@Sql("classpath:/h2/create_tables.sql")
//	@Sql("classpath:/h2/data.sql")
//	@Sql(value = "classpath:/h2/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addAndGetProductTypeTest() {
		ProductType productType = new ProductType();
		productType.setName("dummy_type");
		int rc = dao.addProductType(productType);
		assertEquals(6, rc);
		
		ProductType extractedProductType = dao.getProductTypeById(6);
		assertNotNull(extractedProductType);
		assertEquals(extractedProductType.getId(), Integer.valueOf(6));
		assertEquals(extractedProductType.getName(), "dummy_type");
	}
	
	@Test
//	@Sql("classpath:/h2/create_tables.sql")
//	@Sql("classpath:/h2/data.sql")
//	@Sql(value = "classpath:/h2/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductTypesTest() {
		List<ProductType> productTypes = dao.getAllProductTypes();
		assertNotNull(productTypes);
		assertEquals(productTypes.size(), 5);
		
		ProductType productTypeToFind = new ProductType();
		productTypeToFind.setId(3);
		productTypeToFind.setName("Videocard");

		assertTrue(productTypes.contains(productTypeToFind));
	}
	
	
}
