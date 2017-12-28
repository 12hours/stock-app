package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;

public class ProductIncomeServiceJpa extends AbstractDao<ProductIncome> implements ProductIncomeService {

    public ProductIncomeServiceJpa(EntityManager em) {
        super(ProductIncome.class);
        setEntityManager(em);
    }

    @Override
    public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        return null;
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
