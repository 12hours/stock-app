package com.epam.mentoring.data.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Supplier;

public interface ISupplierDao {
	List<Supplier> getAllSuppliers() throws DataAccessException;
	int addSupplier(Supplier supplier) throws DataAccessException;
	int updateSupplier(Supplier supplier) throws DataAccessException;
	int deleteSupplier(int id) throws DataAccessException;
	Supplier getSupplierById(int id) throws DataAccessException;
}
