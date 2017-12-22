package com.epam.mentoring.data.dao;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.util.mappers.ProductIncomeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductIncomeDaoImpl implements ProductIncomeDao {

	private JdbcTemplate jdbcTemplate;

	@Value("${product_income.get.by_id}")
	private String GET_PRODUCT_INCOME_BY_ID_SQL;
	
	@Value("${product_income.add}")
	private String ADD_PRODUCT_INCOME_SQL;

	@Value("${product_income.update}")
	private String UPDATE_PRODUCT_INCOME_SQL;

	@Value("${product_income.delete}")
	private String DELETE_PRODUCT_INCOME_SQL;

	@Autowired
	private ProductIncomeRowMapper productIncomeRowMapper;

	@Autowired
	public ProductIncomeDaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
		return jdbcTemplate.queryForObject(GET_PRODUCT_INCOME_BY_ID_SQL, new Object[] {id}, productIncomeRowMapper);
	}

	@Override
	public Integer addProductIncome(ProductIncome productIncome) throws DataAccessException {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(ADD_PRODUCT_INCOME_SQL, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1, new java.sql.Date(productIncome.getDate().getTime()));
                preparedStatement.setLong(2, productIncome.getOrderNumber());
                preparedStatement.setInt(3, productIncome.getQuantity());
                preparedStatement.setInt(4, productIncome.getProduct().getId());
                preparedStatement.setInt(5, productIncome.getSupplier().getId());
                preparedStatement.setInt(6, productIncome.getUser().getId());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
	}

	@Override
	public Integer deleteProductIncome(Integer id) throws DataAccessException {
        int delete = jdbcTemplate.update(DELETE_PRODUCT_INCOME_SQL, id);
        if (delete != 1) throw new DataAccessException("Can not perform deletion"){};
        return null;
	}

	@Override
	public Integer updateProductIncome(ProductIncome productIncome) throws DataAccessException {
		int update = jdbcTemplate.update(UPDATE_PRODUCT_INCOME_SQL, productIncome.getDate(),
				productIncome.getOrderNumber(),
				productIncome.getQuantity(),
				productIncome.getProduct().getId(),
				productIncome.getSupplier().getId(),
				productIncome.getUser().getId(),
				productIncome.getId());
		if (update != 1) throw new DataAccessException("Can not perform update"){};
		return null;
	}

	@Override
	public List<ProductIncome> getAllProductIncomes() throws DataAccessException {
		return null;
	}

}
