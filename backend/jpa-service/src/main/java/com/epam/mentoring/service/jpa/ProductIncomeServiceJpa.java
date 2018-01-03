package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

public class ProductIncomeServiceJpa extends AbstractDao<ProductIncome> implements ProductIncomeService {

    public ProductIncomeServiceJpa() {
        super(ProductIncome.class);
    }

    @Override
    public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
        ProductIncome productIncome = find(id);
        return productIncome;
    }

    @Override
    public Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        try {
            persist(productIncome);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save ProductIncome", e) {};
        }
        return productIncome.getId();
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteProductIncome(Integer id) throws DataAccessException {

    }

    @Override
    public void updateProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {

    }
}
