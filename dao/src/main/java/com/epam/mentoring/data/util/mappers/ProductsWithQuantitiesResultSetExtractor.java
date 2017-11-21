package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;

@Component
public class ProductsWithQuantitiesResultSetExtractor implements ResultSetExtractor<Map<Product,Integer>>{

	@Value("${product.column.id}")
	private String PRODUCT_ID_COL;
	
	@Value("${product.column.price}")
	private String PRODUCT_PRICE_COL;
	
	@Value("${product.column.name}")
	private String PRODUCT_NAME_COL;
	
	@Value("${product_type.column.id}")
	private String PRODUCT_TYPE_ID_COL;
	
	@Value("${product_type.column.name}")
	private String PRODUCT_TYPE_NAME_COL;
	
	@Value("${product.column.quantity}")
	private String PRODUCT_QUANTITY_COL;

	@Override
	public Map<Product, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Product, Integer> map = new HashMap<>();
		while (rs.next()) {
			Product product =  new Product();
			product.setId(rs.getInt(PRODUCT_ID_COL));
			product.setPrice(rs.getBigDecimal(PRODUCT_PRICE_COL));
			product.setName(rs.getString(PRODUCT_NAME_COL));
			ProductType productType = new ProductType();
			productType.setId(rs.getInt(PRODUCT_TYPE_ID_COL));
			productType.setName(rs.getString(PRODUCT_TYPE_NAME_COL));
			product.setType(productType);
			map.put(product, rs.getInt(PRODUCT_QUANTITY_COL));
		}
		return map;
	}
}
