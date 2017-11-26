package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
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
    public Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        Assert.notNull(productIncome, "No ProductIncome provided");
        Assert.notNull(productIncome.getProduct(), "No Product for product income");
        Assert.notNull(productIncome.getProduct().getId(), "No Product id for product income");
        Assert.notNull(productIncome.getDate(), "No date for product income");
        Assert.notNull(productIncome.getOrderNumber(), "No date for product income");
        Assert.notNull(productIncome.getQuantity(), "No quantity provided for product income");
        Assert.notNull(productIncome.getSupplier(), "No supplier provided for product income");
        Assert.notNull(productIncome.getSupplier().getId(), "No supplier id provided for product income");
        Assert.notNull(productIncome.getUser(), "No user provided for product income");
        Assert.notNull(productIncome.getUser().getId(), "No user id provided for product income");
        logger.debug("Saving ProductIncome " + productIncome.toString());
        return productIncomeDao.addProductIncome(productIncome);
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {
        System.out.println(productIncomeForm);
        ProductIncome productIncome = DTOUtils.map(productIncomeForm, ProductIncome.class);
        System.out.println(productIncome);
        return saveProductIncome(productIncome);
    }
}
