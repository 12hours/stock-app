package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;

import com.epam.mentoring.data.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierRowMapper implements RowMapper<Supplier>{
	
	@Value("${supplier.column.id}")
	private String SUPPLIER_ID_COL;
	
	@Value("${supplier.column.name}")
	private String SUPPLIER_NAME_COL;
	
	@Value("${supplier.column.details}")
	private String SUPPLIER_DETAILS_COL;

	@Override
	public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
		Supplier supplier = new Supplier();
		supplier.setId(rs.getInt(SUPPLIER_ID_COL));
		supplier.setName(rs.getString(SUPPLIER_NAME_COL));
		supplier.setDetails(rs.getString(SUPPLIER_DETAILS_COL));
		return supplier;
	}
}
