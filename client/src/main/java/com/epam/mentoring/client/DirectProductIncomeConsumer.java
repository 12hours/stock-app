package com.epam.mentoring.client;

import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.model.ProductIncome;
import org.springframework.util.Assert;

import java.util.List;

public class DirectProductIncomeConsumer implements IProductIncomeConsumer {

    private IProductIncomeDao dao;

    public DirectProductIncomeConsumer(IProductIncomeDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer addProductIncome(ProductIncome productIncome) {
        Assert.notNull(productIncome, "No ProductIncome object provided for saving");
        return dao.addProductIncome(productIncome);
    }

    @Override
    public ProductIncome findProductIncome(Integer id) {
        Assert.notNull(id, "No id provided for search");
        return dao.getProductIncomeById(id);
    }

    @Override
    public List<ProductIncome> findAll() {
        // TODO: implement
        return null;
    }
}
