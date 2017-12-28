package com.epam.mentoring.service;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import org.springframework.dao.DataAccessException;

/**
 * Interface for ProductIncome service
 */
public interface ProductIncomeService {

    ProductIncome getProductIncomeById(Integer id) throws DataAccessException;

    Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException;

    Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException;

    void deleteProductIncome(Integer id) throws DataAccessException;

    void updateProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException;

}
