package com.epam.mentoring.data.dao;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.ProductIncome;

import java.util.List;

/**
 * Interface for ProductIncome DAO
 */
public interface ProductIncomeDao {

	/**
	 * Finds ProductIncome record with specified id
	 * @param id target ProductIncome's id
	 * @return ProductIncome
	 * @throws DataAccessException
	 */
	ProductIncome getProductIncomeById(Integer id) throws DataAccessException;

	/**
	 * Saves provided ProductIncome object into database
	 * @param productIncome
	 * @return not specified
	 * @throws DataAccessException
	 */
	Integer addProductIncome(ProductIncome productIncome) throws DataAccessException;

	/**
	 * Deletest ProductIncome record with specified id from database
	 * @param id target ProductIncome's id
	 * @return not specified
	 * @throws DataAccessException
	 */
	Integer deleteProductIncome(Integer id) throws DataAccessException;

	/**
	 * Updates ProductIncome record;
	 * ProductIncome is identified by id field;
	 * @param productIncome updated object
	 * @return not specified
	 * @throws DataAccessException
	 */
	Integer updateProductIncome(ProductIncome productIncome) throws DataAccessException;

	/**
	 * Finds all ProductIncome records in database
	 * @return List of ProductIncome objects
	 * @throws DataAccessException
	 */
	List<ProductIncome> getAllProductIncomes() throws DataAccessException;
}
