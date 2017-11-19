package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.model.ProductIncome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

public class ProductIncomeService implements IProductIncomeService {

    private static final Logger logger = LoggerFactory.getLogger(ProductIncomeService.class.getName());

    private final IProductIncomeDao productIncomeDao;

    public ProductIncomeService(IProductIncomeDao productIncomeDao) {
        this.productIncomeDao = productIncomeDao;
    }

    @Override
    public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id for ProductIncome provided");
        logger.debug("Getting ProductIncome with id " + id);
        return productIncomeDao.getProductIncomeById(id);
    }

    @Override
    public int saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        Assert.notNull(productIncome, "No ProductIncome provided");
        logger.debug("Saving ProductIncome " + productIncome.toString());
        return productIncomeDao.addProductIncome(productIncome);
    }
}
