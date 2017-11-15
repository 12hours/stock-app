package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.model.ProductIncome;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

public class ProductIncomeService implements IProductIncomeService {

    private final IProductIncomeDao productIncomeDao;

    public ProductIncomeService(IProductIncomeDao productIncomeDao) {
        this.productIncomeDao = productIncomeDao;
    }

    @Override
    public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id for ProductIncome provided");
        return productIncomeDao.getProductIncomeById(id);
    }

    @Override
    public int saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        Assert.notNull(productIncome, "No ProductIncome provided");
        return productIncomeDao.addProductIncome(productIncome);
    }
}
