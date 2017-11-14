package com.epam.mentoring.data.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.Supplier;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class SupplierDaoImplTest {
	
	@Autowired
	private ISupplierDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	@Sql("classpath:create_tables.sql")
	@Sql("classpath:data.sql")
	@Sql(value = "classpath:delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addAndGetSupplierTest() {
		Supplier supplier = new Supplier();
		supplier.setName("test supplier");
		supplier.setDetails("details D");
		dao.addSupplier(supplier);
		
		Supplier extractedSupplier = dao.getSupplierById(4);
		assertThat(extractedSupplier, notNullValue());
		assertThat(extractedSupplier.getId(), equalTo(4));
		assertThat(extractedSupplier.getName(), equalTo("test supplier"));
		assertThat(extractedSupplier.getDetails(), equalTo("details D"));
	}
	
	@Test
	@Sql("classpath:create_tables.sql")
	@Sql("classpath:data.sql")
	@Sql(value = "classpath:delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllSuppliersTest() {
		List<Supplier> suppliers = dao.getAllSuppliers();
		assertNotNull(suppliers);
		assertThat(suppliers.size(), equalTo(3));
		
		Supplier supplierToFind = new Supplier();
		supplierToFind.setId(1);
		supplierToFind.setName("Nova Computers");
		supplierToFind.setDetails("details A");
		assertTrue(suppliers.contains(supplierToFind));
	}
	
}
