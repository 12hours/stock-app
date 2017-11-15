package com.epam.mentoring.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.mentoring.data.TestConfig;
import com.epam.mentoring.data.model.ProductType;

@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductTypeDaoImplTest {

	@Autowired
	private IProductTypeDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@Sql("classpath:create_tables.sql")
	@Sql("classpath:data.sql")
	@Sql(value = "classpath:delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addAndGetProductTypeTest() {
		ProductType productType = new ProductType();
		productType.setTypeName("dummy_type");
		int rc = dao.addProductType(productType);
		assertEquals(rc, 1);
		
		ProductType extractedProductType = dao.getProductTypeById(6);
		assertNotNull(extractedProductType);
		assertEquals(extractedProductType.getId(), 6);
		assertEquals(extractedProductType.getTypeName(), "dummy_type");
	}
	
	@Test
	@Sql("classpath:create_tables.sql")
	@Sql("classpath:data.sql")
	@Sql(value = "classpath:delete_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllProductTypesTest() {
		List<ProductType> productTypes = dao.getAllProductTypes();
		assertNotNull(productTypes);
		assertEquals(productTypes.size(), 5);
		
		ProductType productTypeToFind = new ProductType();
		productTypeToFind.setId(3);
		productTypeToFind.setTypeName("Videocard");

		assertTrue(productTypes.contains(productTypeToFind));
	}
	
	
}
