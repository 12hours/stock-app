package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.ProductIncomeConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;

import java.util.List;

public class RestProductIncomeConsumer implements ProductIncomeConsumer {
    @Override
    public Integer addProductIncome(ProductIncome productIncome) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer addProductIncome(ProductIncomeForm productIncomeForm) throws ServerDataAccessException {
        return null;
    }

    @Override
    public ProductIncome findProductIncome(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductIncome> findAll() throws ServerDataAccessException {
        return null;
    }
}
