package com.epam.mentoring.data.dao;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Product;

/**
 * Product DAO interface.
 * Describes basic CRUD operations and some application specifig operations e.g. {@code getAllProductsWithQuantities()}
 */
public interface ProductDao {
	/**
	 * Finds {@code Product} object
	 * @param id target product's id
	 * @return Product
	 * @throws DataAccessException
	 */
	Product getProductById(Integer id) throws DataAccessException;

	/**
	 * Finds all products persisted in database
	 * @return list of all products
	 * @throws DataAccessException
	 */
	List<Product> getAllProducts() throws DataAccessException;

	/**
	 * Finds specific product's quantity (amount of product items in stock)
	 * @param id target product's id
	 * @return product's quantity
	 * @throws DataAccessException
	 */
	int getProductQuantityById(Integer id) throws DataAccessException;

	/**
	 * Finds all products persisted in database with associated quantities
	 * @return Map of products and their qiantities
	 * @throws DataAccessException
	 */
	Map<Product,Integer> getAllProductsWithQuantities() throws DataAccessException;

	/**
	 * Saves product to database
	 * @param product product to save
	 * @return not specified
	 * @throws DataAccessException
	 */
	Integer addProduct(Product product) throws DataAccessException;

	/**
	 * Updates product.
	 * Product record to update is identified by {@code id} field.
	 * @param product
	 * @return not specified
	 * @throws DataAccessException
	 */
	int updateProduct(Product product) throws DataAccessException;

	/**
	 * Deletes product
	 * @param id target product's id
	 * @return not specified
	 * @throws DataAccessException
	 */
	int deleteProduct(Integer id) throws DataAccessException;

	/**
	 * Gets all products and thier quantities represented as {@code ProductWithQuantityView} objects
	 * @return list of ProductWithQuantity objects
	 * @throws DataAccessException
	 */
	List<ProductWithQuantityView> getAllProductsWithQuantitesAsViews() throws DataAccessException;

}
