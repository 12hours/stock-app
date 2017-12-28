package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.epam.mentoring.data.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SuppliersResultSetExtractor implements ResultSetExtractor<List<Supplier>>{

	@Value("${supplier.column.id}")
	private String SUPPLIER_ID_COL;
	
	@Value("${supplier.column.name}")
	private String SUPPLIER_NAME_COL;
	
	@Value("${supplier.column.details}")
	private String SUPPLIER_DETAILS_COL;
	
	@Override
	public List<Supplier> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Supplier> suppliers = new ArrayList<>();
		while (rs.next()) {
			Supplier supplier = new Supplier();
			supplier.setId(rs.getInt(SUPPLIER_ID_COL));
			supplier.setName(rs.getString(SUPPLIER_NAME_COL));
			supplier.setDetails(rs.getString(SUPPLIER_DETAILS_COL));
			suppliers.add(supplier);
		}
		return suppliers;
	}

}
