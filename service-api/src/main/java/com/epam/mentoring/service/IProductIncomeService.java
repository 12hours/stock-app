package com.epam.mentoring.service;

import com.epam.mentoring.data.model.ProductIncome;
import org.springframework.dao.DataAccessException;

public interface IProductIncomeService {
    ProductIncome getProductIncomeById(Integer id) throws DataAccessException;
    int saveProductIncome(ProductIncome productIncome) throws DataAccessException;
}
