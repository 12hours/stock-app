package com.epam.mentoring.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

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
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.User;

import javax.sql.DataSource;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductIncomeDaoImplTest {

	@Autowired
	private ProductIncomeDao dao;

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
	public void getProductIncomeTest() {
		ProductIncome productIncome = dao.getProductIncomeById(Integer.valueOf(1));
		assertNotNull(productIncome);
		assertEquals(Integer.valueOf(1), productIncome.getId());
		assertEquals(Long.valueOf(10001), productIncome.getOrderNumber());
		assertEquals(Integer.valueOf(10), productIncome.getQuantity());
		Calendar dateGoal = Calendar.getInstance();
		dateGoal.set(2017, 9, 25);
//		Date date = new Date(dateCalendar.getTimeInMillis());
		Calendar dateExtracted = Calendar.getInstance();
		dateExtracted.setTime(productIncome.getDate());
		assertEquals(dateGoal.get(dateGoal.YEAR), dateExtracted.get(dateExtracted.YEAR));
		assertEquals(dateGoal.get(dateGoal.MONTH), dateExtracted.get(dateExtracted.MONTH));
		assertEquals(dateGoal.get(dateGoal.DAY_OF_MONTH), dateExtracted.get(dateExtracted.DAY_OF_MONTH));
		
		ProductType productTypeGoal = new ProductType();
		productTypeGoal.setId(1);
		productTypeGoal.setName("CPU");
		Product productGoal = new Product();
		productGoal.setId(1);
		productGoal.setName("Intel Core i7 8700");
		productGoal.setPrice(BigDecimal.valueOf(360));
		productGoal.setType(productTypeGoal);
		assertEquals(productGoal, productIncome.getProduct());
		
		Supplier supplierGoal = new Supplier();
		supplierGoal.setId(1);
		supplierGoal.setName("Nova Computers");
		supplierGoal.setDetails("");
		assertEquals(supplierGoal, productIncome.getSupplier());
		
		User userGoal = productIncome.getUser();
		userGoal.setId(1);
		userGoal.setName("accounter");
		userGoal.setPassword("12345");
		assertEquals(userGoal, productIncome.getUser());
	}
	
	@Test
//	@Sql("classpath:/h2/create_tables.sql")
//	@Sql("classpath:/h2/data.sql")
//	@Sql(value = "classpath:/h2/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addProductIncomeTest() {
		ProductIncome productIncome = new ProductIncome();
		Product product = new Product();
		product.setId(1);
		User user = new User();
		user.setId(1);
		Supplier supplier = new Supplier();
		supplier.setId(1);
		
		productIncome.setOrderNumber(10015L);
		productIncome.setQuantity(16);
		Calendar date = Calendar.getInstance();
		date.set(2017, 9, 25);
		productIncome.setDate(new Date(date.getTimeInMillis()));
		productIncome.setProduct(product);
		productIncome.setSupplier(supplier);
		productIncome.setUser(user);
		
		dao.addProductIncome(productIncome);
		
		
		ProductIncome productIncomeExtracted = dao.getProductIncomeById(Integer.valueOf(13));
		assertNotNull(productIncomeExtracted);
		assertEquals(Integer.valueOf(13), productIncomeExtracted.getId());
		assertEquals(Long.valueOf(10015), productIncomeExtracted.getOrderNumber());
		assertEquals(Integer.valueOf(16), productIncomeExtracted.getQuantity());
		Calendar dateGoal = Calendar.getInstance();
		dateGoal.set(2017, 9, 25);
//		Date date = new Date(dateCalendar.getTimeInMillis());
		Calendar dateExtracted = Calendar.getInstance();
		dateExtracted.setTime(productIncomeExtracted.getDate());
		assertEquals(dateGoal.get(dateGoal.YEAR), dateExtracted.get(dateExtracted.YEAR));
		assertEquals(dateGoal.get(dateGoal.MONTH), dateExtracted.get(dateExtracted.MONTH));
		assertEquals(dateGoal.get(dateGoal.DAY_OF_MONTH), dateExtracted.get(dateExtracted.DAY_OF_MONTH));
		
		ProductType productTypeGoal = new ProductType();
		productTypeGoal.setId(1);
		productTypeGoal.setName("CPU");
		Product productGoal = new Product();
		productGoal.setId(1);
		productGoal.setName("Intel Core i7 8700");
		productGoal.setPrice(BigDecimal.valueOf(360));
		productGoal.setType(productTypeGoal);
		assertEquals(productGoal, productIncomeExtracted.getProduct());
		
		Supplier supplierGoal = new Supplier();
		supplierGoal.setId(1);
		supplierGoal.setName("Nova Computers");
		supplierGoal.setDetails("");
		assertEquals(supplierGoal, productIncomeExtracted.getSupplier());
		
		User userGoal = productIncomeExtracted.getUser();
		userGoal.setId(1);
		userGoal.setName("accounter");
		userGoal.setPassword("12345");
		assertEquals(userGoal, productIncomeExtracted.getUser());
		
	}

}
