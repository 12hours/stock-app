package com.epam.mentoring.data.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Supplier;

/**
 * Interface for {@code Supplier} DAO;
 * Describes basic CRUD operations
 */
public interface SupplierDao {

	/**
	 * Finds all {@code Supplier} records in database
	 * @return list of suppliers
	 * @throws DataAccessException
	 */
	List<Supplier> getAllSuppliers() throws DataAccessException;

	/**
	 * Saves provided {@code Supplier} object to database
	 * @param supplier Supplier to save
	 * @return not specified
	 * @throws DataAccessException
	 */
	int addSupplier(Supplier supplier) throws DataAccessException;

	/**
	 * Updates {@code Supplier} record;
	 * Supplier record is identified by id field of provided {@code Supplier} object
	 * @param supplier updated Supplier
	 * @return not specified
	 * @throws DataAccessException
	 */
	int updateSupplier(Supplier supplier) throws DataAccessException;

	/**
	 * Deletes {@code Supplier} record with specified id from database
	 * @param id id of record to delete
	 * @return not specified
	 * @throws DataAccessException
	 */
	int deleteSupplier(Integer id) throws DataAccessException;

	/**
	 * Finds {@code Supplier} record with specified id
	 * @param id target supplier id
	 * @return Supplier
	 * @throws DataAccessException
	 */
	Supplier getSupplierById(Integer id) throws DataAccessException;
}
