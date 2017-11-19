package com.epam.mentoring.data.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.epam.mentoring.data.model.ProductIncome;
import org.springframework.stereotype.Component;

@Component
public class ProductIncomesResultSetExtractor implements ResultSetExtractor<List<ProductIncome>> {

	@Override
	public List<ProductIncome> extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
