package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductRowMapper implements RowMapper<Product>{

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
	
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = null;
		product = new Product();
		product.setId(rs.getInt(PRODUCT_ID_COL));
		product.setPrice(rs.getBigDecimal(PRODUCT_PRICE_COL));
		product.setName(rs.getString(PRODUCT_NAME_COL));
		ProductType productType = new ProductType();
		productType.setId(rs.getInt(PRODUCT_TYPE_ID_COL));
		productType.setName(rs.getString(PRODUCT_TYPE_NAME_COL));
		product.setType(productType);
		return product;
	}

}
