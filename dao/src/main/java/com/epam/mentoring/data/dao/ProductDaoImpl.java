package com.epam.mentoring.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.data.util.mappers.ProductResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.util.mappers.ProductRowMapper;
import com.epam.mentoring.data.util.mappers.ProductsWithQuantitiesResultSetExtractor;

public class ProductDaoImpl implements IProductDao{
	
	@Value("${product.get.by_id}")
	private String GET_PRODUCT_BY_ID_SQL;
	
	@Value("${product.get.all}")
	private String GET_ALL_PRODUCTS_SQL;
	
	@Value("${product.add}")
	private String ADD_PRODUCT_SQL;
	
	@Value("${product.update}")
	private String UPDATE_PRODUCT_SQL;
	
	@Value("${product.delete}")
	private String DELETE_PRODUCT_SQL;
	
	@Value("${product.quantity.get.by_id}")
	private String GET_PRODUCT_QUANTITY_BY_ID_SQL;
	
	@Value("${product.quantity.get.all}")
	private String GET_ALL_PRODUCTS_WITH_QUANTITIES_SQL;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ProductRowMapper productRowMapper;

	@Autowired
	ProductResultSetExtractor productResultSetExtractor;
	
	@Autowired
	ProductsWithQuantitiesResultSetExtractor productsWithQuantitiesMapper;
	
	public ProductDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product getProductById(Integer id) {
		Product product = jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID_SQL, new Object[] {id}, productRowMapper);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		return jdbcTemplate.query(GET_ALL_PRODUCTS_SQL, productResultSetExtractor);
	}

	@Override
	public int addProduct(Product product) {
		return jdbcTemplate.update(ADD_PRODUCT_SQL, product.getName(), product.getPrice(), product.getType().getId());
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductWithQuantityView> getAllProductsWithQuantitesAsViews() throws DataAccessException {
		Map<Product, Integer> allProductsWithQuantities = this.getAllProductsWithQuantities();
		List<ProductWithQuantityView> productWithQuantityViewList = new ArrayList<>();
		for (Map.Entry<Product, Integer> entry : allProductsWithQuantities.entrySet()) {
			Product product = entry.getKey();
			ProductWithQuantityView productWithQuantityView = new ProductWithQuantityView();
			productWithQuantityView.setId(product.getId());
			productWithQuantityView.setProductName(product.getName());
			productWithQuantityView.setQuantity(entry.getValue());
			productWithQuantityViewList.add(productWithQuantityView);
		}
		return productWithQuantityViewList;
	}

	@Override
	public int getProductQuantityById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Product, Integer> getAllProductsWithQuantities() {
		Map<Product, Integer> map = jdbcTemplate.query(GET_ALL_PRODUCTS_WITH_QUANTITIES_SQL, productsWithQuantitiesMapper);
		return map;
	}


}
