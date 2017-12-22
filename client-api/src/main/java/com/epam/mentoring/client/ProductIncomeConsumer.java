package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;

import java.util.List;

public interface ProductIncomeConsumer {

    Integer saveProductIncome(ProductIncome productIncome) throws ServerDataAccessException;

    Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws ServerDataAccessException;

    ProductIncome findProductIncome(Integer id) throws ServerDataAccessException;

    List<ProductIncome> findAll() throws ServerDataAccessException;

    void updateProductIncome(Integer id, ProductForm productForm) throws ServerDataAccessException;

    void deleteProductIncome(Integer id) throws ServerDataAccessException;

}
