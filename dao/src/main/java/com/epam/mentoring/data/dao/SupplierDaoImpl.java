package com.epam.mentoring.data.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.util.mappers.SupplierRowMapper;
import com.epam.mentoring.data.util.mappers.SuppliersResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierDaoImpl implements SupplierDao {

	@Value("${supplier.get.by_id}")
	private String GET_SUPPLIER_BY_ID_SQL;
	
	@Value("${supplier.get.all}")
	private String GET_ALL_SUPPLIERS_SQL;
	
	@Value("${supplier.add}")
	private String ADD_SUPPLIER_SQL;
	
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
	public int addSupplier(Supplier supplier) throws DataAccessException {
		return jdbcTemplate.update(ADD_SUPPLIER_SQL, supplier.getName(), supplier.getDetails());
	}

	@Override
	public int updateSupplier(Supplier supplier) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSupplier(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
