package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.User;

public class ProductIncomeRowMapper implements RowMapper<ProductIncome> {

	@Value("${product_income.column.id}")
	private String PRODUCT_INCOME_ID_COL;
	
	@Value("${product_income.column.date}")
	private String PRODUCT_INCOME_DATE;
	
	@Value("${product_income.column.quantity}")
	private String PRODUCT_INCOME_QUANTITY;

	@Value("${product_income.column.order_number}")
	private String PRODUCT_INCOME_ORDER_NUMBER;
	
	@Value("${product_income.column.product_id}")
	private String PRODUCT_INCOME_PRODUCT_ID;
	
	@Value("${product_income.column.supplier_id}")
	private String PRODUCT_INCOME_SUPPLIER_ID;
	
	@Value("${product_income.column.user_id}")
	private String PRODUCT_INCOME_USER_ID;
	
	@Value("${product_type.column.id}")
	private String PRODUCT_TYPE_ID_COL;
	
	@Value("${product_type.column.name}")
	private String PRODUCT_TYPE_NAME_COL;
	
	@Value("${product.column.id}")
	private String PRODUCT_ID_COL;
	
	@Value("${product.column.price}")
	private String PRODUCT_PRICE_COL;
	
	@Value("${product.column.name}")
	private String PRODUCT_NAME_COL;
	
	@Value("${supplier.column.id}")
	private String SUPPLIER_ID_COL;
	
	@Value("${supplier.column.name}")
	private String SUPPLIER_NAME_COL;
	
	@Value("${supplier.column.details}")
	private String SUPPLIER_DETAILS_COL;
	
	@Value("${user.column.id}")
	private String USER_ID_COL;
	
	@Value("${user.column.name}")
	private String USER_NAME_COL;
	
	@Value("${user.column.password}")
	private String USER_PASSWORD_COL;
	
	@Value("${user.column.privileges}")
	private String USER_PRIVILEGES_COL;

	@Override
	public ProductIncome mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductType productType = new ProductType();
		productType.setId(rs.getInt(PRODUCT_TYPE_ID_COL));
		productType.setName(rs.getString(PRODUCT_TYPE_NAME_COL));
		Product product = null;
		product = new Product();
		product.setId(rs.getInt(PRODUCT_ID_COL));
		product.setPrice(rs.getBigDecimal(PRODUCT_PRICE_COL));
		product.setProductName(rs.getString(PRODUCT_NAME_COL));
		product.setType(productType);
		Supplier supplier = new Supplier();
		supplier.setId(rs.getInt(SUPPLIER_ID_COL));
		supplier.setName(rs.getString(SUPPLIER_NAME_COL));
		supplier.setDetails(rs.getString(SUPPLIER_DETAILS_COL));
		User user = new User();
		user.setId(rs.getInt(USER_ID_COL));
		user.setName(rs.getString(USER_NAME_COL));
		user.setPassword(rs.getString(USER_PASSWORD_COL));
		user.setAdmin(rs.getBoolean(USER_PRIVILEGES_COL));
		ProductIncome productIncome = new ProductIncome();
		productIncome.setId(rs.getInt(PRODUCT_INCOME_ID_COL));
		productIncome.setOrderNumber(rs.getLong(PRODUCT_INCOME_ORDER_NUMBER));
		productIncome.setDate(rs.getDate(PRODUCT_INCOME_DATE));
		productIncome.setQuantity(rs.getInt(PRODUCT_INCOME_QUANTITY));
		productIncome.setProduct(product);
		productIncome.setSupplier(supplier);
		productIncome.setUser(user);
		
		return productIncome;
	}

}
