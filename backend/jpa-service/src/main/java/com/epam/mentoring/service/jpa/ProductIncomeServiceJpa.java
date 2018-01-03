package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManagerFactory;

public class ProductIncomeServiceJpa extends AbstractDao<ProductIncome> implements ProductIncomeService {

    public ProductIncomeServiceJpa(EntityManagerFactory emf) {
        super(ProductIncome.class, emf);
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
        ProductIncome productIncome = DTOUtils.map(productIncomeForm, ProductIncome.class);
        return saveProductIncome(productIncome);
    }

    @Override
    public void deleteProductIncome(Integer id) throws DataAccessException {
        remove(id);
    }

    @Override
    public void updateProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {

    }
}
