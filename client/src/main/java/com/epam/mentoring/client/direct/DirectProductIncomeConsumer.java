package com.epam.mentoring.client.direct;

import com.epam.mentoring.client.ProductIncomeConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.IProductIncomeService;
import org.springframework.util.Assert;

import java.util.List;

public class DirectProductIncomeConsumer implements ProductIncomeConsumer {

    private IProductIncomeService productIncomeService;

    public DirectProductIncomeConsumer(IProductIncomeService service) {
        this.productIncomeService = service;
    }

    @Override
    public Integer saveProductIncome(ProductIncome productIncome) {
        Assert.notNull(productIncome, "No ProductIncome object provided for saving");
        return productIncomeService.saveProductIncome(productIncome);
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws ServerDataAccessException {
        return null;
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
