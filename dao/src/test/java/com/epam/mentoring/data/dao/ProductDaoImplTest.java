package com.epam.mentoring.data.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoImplTest {
	
	Logger logger = LoggerFactory.getLogger(ProductDaoImplTest.class);

	@Autowired
	@Qualifier("ProductDaoImpl")
	private IProductDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	@Sql("classpath:create_tables.sql")
	@Sql("classpath:data.sql")
	@Sql(value = "classpath:delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductsWithQuantitesTest() {
		logger.info("getAllProductsWithQuantitesTest run");
		Map<Integer, Integer> expectedQuantitiesMap = new HashMap<Integer, Integer>() {
			{
				put(1, 5);
				put(2, 27);
				put(3, 29);
				put(4, 50);
				put(5, 5);
				put(6, 25);
				put(7, 7);
				put(8, 30);
				put(9, 12);
			}
		};
		
		Map<Product, Integer> productsAndQuantitesMap = dao.getAllProductsWithQuantities();
		assertThat(productsAndQuantitesMap.size(), is(9));
		
		for(Map.Entry<Product, Integer> entry: productsAndQuantitesMap.entrySet()) {
			assertThat(entry.getValue(), equalTo(expectedQuantitiesMap.get(entry.getKey().getId()))); 
		}
	}
	
	@Test
	@Sql("classpath:/create_tables.sql")
	@Sql("classpath:/data.sql")
	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductsWithQuantitesUpdateValuesTest() {
		Map<Integer, Integer> expectedQuantitiesMap = new HashMap<Integer, Integer>() {
			{
				put(1, 20);
				put(2, 27);
				put(3, 34);
				put(4, 42);
				put(5, 23);
				put(6, 25);
				put(7, 15);
				put(8, 18);
				put(9, 9);
			}
		};
		
		jdbcTemplate.execute("INSERT INTO product_income VALUES (13, 15, 10013, '2017-11-10', 1, 1, 1)");
		jdbcTemplate.execute("INSERT INTO product_income VALUES (14, 10, 10014, '2017-11-10', 3, 1, 1)");
		jdbcTemplate.execute("INSERT INTO product_income VALUES (15, 18, 10015, '2017-11-10', 5, 1, 1)");
		jdbcTemplate.execute("INSERT INTO product_income VALUES (16, 8, 10016, '2017-11-10', 7, 1, 1)");
		jdbcTemplate.execute("INSERT INTO product_outcome VALUES (13, 5, '2017-11-10', 3, 1)");
		jdbcTemplate.execute("INSERT INTO product_outcome VALUES (14, 8, '2017-11-10', 4, 1)");
		jdbcTemplate.execute("INSERT INTO product_outcome VALUES (15, 12, '2017-11-10', 8, 1)");
		jdbcTemplate.execute("INSERT INTO product_outcome VALUES (16, 3, '2017-11-10', 9, 1)");
		
		Map<Product, Integer> productsAndQuantitesMap = dao.getAllProductsWithQuantities();
		assertThat(productsAndQuantitesMap.size(), is(9));
		
		for(Map.Entry<Product, Integer> entry: productsAndQuantitesMap.entrySet()) {
			assertThat(entry.getValue(), equalTo(expectedQuantitiesMap.get(entry.getKey().getId()))); 
		}
	}
	
	@Test
	@Sql("classpath:/create_tables.sql")
	@Sql("classpath:/data.sql")
	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductsWithQuantitesOnlyIncomeTest() {
		Map<Integer, Integer> expectedQuantitiesMap = new HashMap<Integer, Integer>() {
			{
				put(1, 10);
				put(2, 40);
				put(3, 45);
				put(4, 50);
				put(5, 25);
				put(6, 50);
				put(7, 10);
				put(8, 30);
				put(9, 25);
			}
		};
		
		jdbcTemplate.execute("DELETE FROM product_outcome");
		
		Map<Product, Integer> productsAndQuantitesMap = dao.getAllProductsWithQuantities();
		assertThat(productsAndQuantitesMap.size(), is(9));
		
		for(Map.Entry<Product, Integer> entry: productsAndQuantitesMap.entrySet()) {
			assertThat(entry.getValue(), equalTo(expectedQuantitiesMap.get(entry.getKey().getId()))); 
		}
	}

	@Test
	@Sql("classpath:/create_tables.sql")
	@Sql("classpath:/data.sql")
	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addProductGetProductTest() {
		
		ProductType productType = new ProductType();
		productType.setId(1);
		
		Product product = new Product();
		product.setPrice(new BigDecimal(100.0));
		product.setProductName("dummy_product");
		product.setType(productType);
		
		int ra = dao.addProduct(product);
		assertThat(Integer.valueOf(ra), equalTo(Integer.valueOf(1)));
		
		Product extractedProduct = dao.getProductById(10);
		assertThat(extractedProduct, notNullValue());
		assertThat(extractedProduct.getId(), equalTo(10));
		assertThat(extractedProduct.getProductName(), equalTo("dummy_product"));
		assertTrue(extractedProduct.getPrice().compareTo(BigDecimal.valueOf(100.0)) == 0);
		assertThat(extractedProduct.getType().getTypeName(), equalTo("CPU"));
		assertThat(extractedProduct.getType().getId(), equalTo(1));
	}
	
	
	
}
