package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;

import java.util.List;

public interface ProductIncomeConsumer {

    Integer addProductIncome(ProductIncome productIncome) throws ServerDataAccessException;

    Integer addProductIncome(ProductIncomeForm productIncomeForm) throws ServerDataAccessException;

    ProductIncome findProductIncome(Integer id) throws ServerDataAccessException;

    List<ProductIncome> findAll() throws ServerDataAccessException;

}
