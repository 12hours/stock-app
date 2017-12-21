package com.epam.mentoring.data.dao;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.Supplier;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class SupplierDaoImplTest {
	
	@Autowired
	private SupplierDao dao;
	
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
	public void addAndGetSupplierTest() {
		Supplier supplier = new Supplier();
		supplier.setName("test supplier");
		supplier.setDetails("details D");
		Integer id = dao.addSupplier(supplier);
		assertEquals(Integer.valueOf(4), id);

		Supplier extractedSupplier = dao.getSupplierById(4);
		assertThat(extractedSupplier, notNullValue());
		assertThat(extractedSupplier.getId(), equalTo(4));
		assertThat(extractedSupplier.getName(), equalTo("test supplier"));
		assertThat(extractedSupplier.getDetails(), equalTo("details D"));
	}
	
	@Test
//	@Sql("classpath:/h2/create_tables.sql")
//	@Sql("classpath:/h2/data.sql")
//	@Sql(value = "classpath:/h2/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllSuppliersTest() {
		List<Supplier> suppliers = dao.getAllSuppliers();
		assertNotNull(suppliers);
		assertThat(suppliers.size(), equalTo(3));
		
		Supplier supplierToFind = new Supplier();
		supplierToFind.setId(1);
		supplierToFind.setName("Nova Computers");
		supplierToFind.setDetails("");
		assertTrue(suppliers.contains(supplierToFind));
	}
	
}
