package com.epam.mentoring.data.dao;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoImplTest {

	Logger logger = LoggerFactory.getLogger(ProductDaoImplTest.class);

	@Autowired
	private ProductDao dao;

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
//	@Sql("classpath:/create_tables.sql")
//	@Sql("classpath:/data.sql")
//	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
//	@Sql("classpath:/create_tables.sql")
//	@Sql("classpath:/data.sql")
//	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
//	@Sql("classpath:/create_tables.sql")
//	@Sql("classpath:/data.sql")
//	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addProductGetProductTest() {

		ProductType productType = new ProductType();
		productType.setId(1);

		Product product = new Product();
		product.setPrice(new BigDecimal(100.0));
		product.setName("dummy_product");
		product.setType(productType);

		int ra = dao.addProduct(product);
		assertThat(Integer.valueOf(ra), equalTo(Integer.valueOf(1)));

		Product extractedProduct = dao.getProductById(10);
		assertThat(extractedProduct, notNullValue());
		assertThat(extractedProduct.getId(), equalTo(10));
		assertThat(extractedProduct.getName(), equalTo("dummy_product"));
		assertTrue(extractedProduct.getPrice().compareTo(BigDecimal.valueOf(100.0)) == 0);
		assertThat(extractedProduct.getType().getName(), equalTo("CPU"));
		assertThat(extractedProduct.getType().getId(), equalTo(1));
	}


	@Test
//	@Sql("classpath:/create_tables.sql")
//	@Sql("classpath:/data.sql")
//	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductsTest() {
		List<Product> products = dao.getAllProducts();
		assertNotNull(products);
		assertEquals(9, products.size());

		ProductType productType_1 = new ProductType(1, "CPU");
		ProductType productType_3 = new ProductType(3, "Videocard");
		Product productToFind_1 = new Product(3, "Nvidia GTX 1050Ti", BigDecimal.valueOf(200), productType_3);
		Product productToFind_2 = new Product(5, "AMD Ryzen 7 1700", BigDecimal.valueOf(325), productType_1);

		assertTrue(products.contains(productToFind_1));
		assertTrue(products.contains(productToFind_2));
	}


	@Test
//	@Sql("classpath:/create_tables.sql")
//	@Sql("classpath:/data.sql")
//	@Sql(value = "classpath:/delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductsWithQuantitesAsViewsTest() throws Exception {
		List<ProductWithQuantityView> productWithQuantityViews = dao.getAllProductsWithQuantitesAsViews();
		assertNotNull(productWithQuantityViews);
		assertEquals(9, productWithQuantityViews.size());

		List<ProductWithQuantityView> expectedQuantitiesList = new ArrayList<ProductWithQuantityView>() {
			{
				add(new ProductWithQuantityView(1, "Intel Core i7 8700", 5));
				add(new ProductWithQuantityView(2, "Intel Core i3 8100", 27));
				add(new ProductWithQuantityView(3, "Nvidia GTX 1050Ti", 29));
				add(new ProductWithQuantityView(4, "Intel Pentium G4360", 50));
				add(new ProductWithQuantityView(5, "AMD Ryzen 7 1700", 5));
				add(new ProductWithQuantityView(6, "Samsung 850 Evo 256 Gb", 25));
				add(new ProductWithQuantityView(7, "Intel Core i5 6600K", 7));
				add(new ProductWithQuantityView(8, "Kingston UV400 120 Gb", 30));
				add(new ProductWithQuantityView(9, "ASRock Z370", 12));
			}
		};

		assertTrue(productWithQuantityViews.containsAll(expectedQuantitiesList));
	}
}
