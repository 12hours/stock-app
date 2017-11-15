package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;

import com.epam.mentoring.data.model.ProductType;

public class ProductTypeRowMapper implements RowMapper<ProductType>{

	@Value("${product_type.column.id}")
	private String PRODUCT_TYPE_ID_COL;
	
	@Value("${product_type.column.name}")
	private String PRODUCT_TYPE_NAME_COL;
	
	@Override
	public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductType productType = new ProductType();
		productType.setId(rs.getInt(PRODUCT_TYPE_ID_COL));
		productType.setTypeName(rs.getString(PRODUCT_TYPE_NAME_COL));
		return productType;
	}
	
}
