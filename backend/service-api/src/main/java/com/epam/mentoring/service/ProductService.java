package com.epam.mentoring.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.Product;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * Interface for Product service
 */
public interface ProductService {
	
	Product findProductById(Integer id) throws DataAccessException;
	
	Integer saveProduct(Product product) throws DataAccessException;

    Integer saveProduct(ProductForm productForm) throws DataAccessException;

    void updateProduct(Product product) throws DataAccessException;

    void updateProduct(Integer id, ProductForm productForm) throws DataAccessException;

    List<Product> getAllProducts() throws DataAccessException;

	Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException;

    /**
     * @param id
     * @throws DataRetrievalFailureException if object with given id not found
     * @throws DataAccessException in any other situation
     */
	void deleteProductById(Integer id) throws DataAccessException;

    List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws DataAccessException;

    Collection<ProductIncome> getIncomesOfProduct(Integer id) throws DataAccessException;

    ProductWithQuantityView getProductWithQuantity(Integer id);
}
