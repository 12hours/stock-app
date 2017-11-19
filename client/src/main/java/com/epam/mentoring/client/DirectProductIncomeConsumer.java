package com.epam.mentoring.client;

import com.epam.mentoring.data.dao.IProductIncomeDao;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.service.IProductIncomeService;
import com.epam.mentoring.service.IProductService;
import org.springframework.util.Assert;

import java.util.List;

public class DirectProductIncomeConsumer implements IProductIncomeConsumer {

    private IProductIncomeService productIncomeService;

    public DirectProductIncomeConsumer(IProductIncomeService service) {
        this.productIncomeService = service;
    }

    @Override
    public Integer addProductIncome(ProductIncome productIncome) {
        Assert.notNull(productIncome, "No ProductIncome object provided for saving");
        return productIncomeService.saveProductIncome(productIncome);
    }

    @Override
    public ProductIncome findProductIncome(Integer id) {
        Assert.notNull(id, "No id provided for search");
        return productIncomeService.getProductIncomeById(id);
    }

    @Override
    public List<ProductIncome> findAll() {
        // TODO: implement
        return null;
    }
}
