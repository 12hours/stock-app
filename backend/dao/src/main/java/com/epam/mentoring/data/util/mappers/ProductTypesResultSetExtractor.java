package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.epam.mentoring.data.model.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypesResultSetExtractor implements ResultSetExtractor<List<ProductType>> {

	@Value("${product_type.column.id}")
	private String PRODUCT_TYPE_ID_COL;
	
	@Value("${product_type.column.name}")
	private String PRODUCT_TYPE_NAME_COL;
	
	@Override
	public List<ProductType> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ProductType> productTypes = new ArrayList<>();
		while (rs.next()) {
			ProductType productType = new ProductType();
			productType.setId(rs.getInt(PRODUCT_TYPE_ID_COL));
			productType.setName(rs.getString(PRODUCT_TYPE_NAME_COL));
			productTypes.add(productType);
		}
		return productTypes;
	}

}
