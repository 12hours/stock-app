package com.epam.mentoring.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.ProductIncome;


public class PureJDBCProductIncomeDaoImpl implements IProductIncomeDao {
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String JDBC_URL = "jdbc:h2:mem:test";
	
	private void registerDriver() {
			try {
				Class.forName(JDBC_DRIVER).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

	}
	
	@Override
	public ProductIncome getProductIncomeById(int id) {
		Connection conn = null;
		ProductIncome productIncome = null;
		try {
			registerDriver();
			conn = DriverManager.getConnection(JDBC_URL);
			PreparedStatement ps = conn.prepareStatement("select * from product_income where id = ?");
			ps.setInt(0, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				productIncome = new ProductIncome();
				productIncome.setId(rs.getInt(1));
				productIncome.setOrderNumber(rs.getLong(2));
				// assemble object
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return productIncome;
	}

	@Override
	public int addProductIncome(ProductIncome productIncome) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}


}
