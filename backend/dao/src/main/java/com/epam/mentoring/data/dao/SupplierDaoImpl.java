package com.epam.mentoring.data.dao;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.util.mappers.SupplierRowMapper;
import com.epam.mentoring.data.util.mappers.SuppliersResultSetExtractor;
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

public class SupplierDaoImpl implements SupplierDao {

	@Value("${supplier.get.by_id}")
	private String GET_SUPPLIER_BY_ID_SQL;
	
	@Value("${supplier.get.all}")
	private String GET_ALL_SUPPLIERS_SQL;
	
	@Value("${supplier.add}")
	private String ADD_SUPPLIER_SQL;

	@Value("${supplier.update}")
	private String UPDATE_SUPPLIER_SQL;


	@Value("${supplier.delete}")
	private String DELETE_SUPPLIER_SQL;

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SupplierRowMapper supplierRowMapper;
	
	@Autowired
	private SuppliersResultSetExtractor suppliersResultSetExtractor;

	@Autowired
	public SupplierDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Supplier getSupplierById(Integer id) throws DataAccessException {
		Supplier supplier = jdbcTemplate.queryForObject(GET_SUPPLIER_BY_ID_SQL, new Object[] {id}, supplierRowMapper);
		return supplier;
	}

	@Override
	public List<Supplier> getAllSuppliers() throws DataAccessException {
		return jdbcTemplate.query(GET_ALL_SUPPLIERS_SQL, suppliersResultSetExtractor);
	}

	@Override
	public Integer addSupplier(Supplier supplier) throws DataAccessException {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(ADD_SUPPLIER_SQL, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, supplier.getName());
                preparedStatement.setString(2, supplier.getDetails());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
	}

	@Override
	public int updateSupplier(Supplier supplier) throws DataAccessException {
        int update = jdbcTemplate.update(UPDATE_SUPPLIER_SQL, supplier.getName(), supplier.getDetails(), supplier.getId());
        if (update != 1) throw new DataAccessException("Can not perform update"){};
        return 0;
	}

	@Override
	public int deleteSupplier(Integer id) throws DataAccessException {
        int delete = jdbcTemplate.update(DELETE_SUPPLIER_SQL, id);
        if (delete != 1) throw new DataAccessException("Can not perform deletion"){};
        return 0;
	}

}
