package com.epam.mentoring.service;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface for ProductIncome service
 */
public interface ProductIncomeService {

    List<ProductIncome> getAllProductIncomes() throws DataAccessException;

    ProductIncome getProductIncomeById(Integer id) throws DataAccessException;

    Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException;

    Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException;

    void deleteProductIncome(Integer id) throws DataAccessException;

    void updateProductIncome(Integer id, ProductIncomeForm productIncomeForm) throws DataAccessException;

    void updateProductIncome(ProductIncome productIncome) throws DataAccessException;

}
