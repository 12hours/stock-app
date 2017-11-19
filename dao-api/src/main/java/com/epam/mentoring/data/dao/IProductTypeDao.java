package com.epam.mentoring.data.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.ProductType;

/**
 * Interface for ProductType DAO.
 * Describes basic CRUD operations.
 */
public interface IProductTypeDao {
	/**
	 * Finds ProductType record with specified id
	 * @param id target ProductType's id
	 * @return ProductType
	 * @throws DataAccessException
	 */
	ProductType getProductTypeById(Integer id) throws DataAccessException;

	/**
	 * Finds all ProductType records in database
	 * @return list of ProductType objects
	 * @throws DataAccessException
	 */
	List<ProductType> getAllProductTypes() throws DataAccessException;

	/**
	 * Updates ProductType record;
	 * Target ProductType record is identified by id field of provided {@code ProductType} object;
	 * @param productType updated {@code ProductType} object
	 * @return not specified
	 * @throws DataAccessException
	 */
	int updateProductType(ProductType productType) throws DataAccessException;

	/**
	 * Saves {@code ProductType} object to database
	 * @param productType object to save
	 * @return not specified
	 * @throws DataAccessException
	 */
	int addProductType(ProductType productType) throws DataAccessException;

	/**
	 * Deletes ProductType record with specified {@code id} from database
	 * @param id target ProducType's id
	 * @return not specified
	 * @throws DataAccessException
	 */
	int deleteProductType(Integer id) throws DataAccessException;
}
