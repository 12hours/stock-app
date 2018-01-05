package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductIncomeServiceJpaImpl extends AbstractDao<ProductIncome> implements ProductIncomeService {

    public ProductIncomeServiceJpaImpl(EntityManagerFactory emf) {
        super(ProductIncome.class, emf);
    }

    @Override
    public List<ProductIncome> getAllProductIncomes() throws DataAccessException {
        try {
            List<ProductIncome> productIncomes = findAll();
            return productIncomes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get ProductIncome list"){};
        }
    }

    @Override
    public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
        try {
            ProductIncome productIncome = find(id);
            return productIncome;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get ProductIncome"){};
        }
    }

    @Override
    public Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        try {
            persist(productIncome);
            return productIncome.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save ProductIncome", e) {};
        }
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {
        ProductIncome productIncome = DTOUtils.map(productIncomeForm, ProductIncome.class);
        return saveProductIncome(productIncome);
    }

    @Override
    public void deleteProductIncome(Integer id) throws DataAccessException {
        try {
            remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not delete ProductIncome"){};
        }
    }

    @Override
    public void updateProductIncome(Integer id, ProductIncomeForm productIncomeForm) throws DataAccessException {
        ProductIncome productIncome = DTOUtils.map(productIncomeForm, ProductIncome.class);
        productIncome.setId(id);
        updateProductIncome(productIncome);
    }

    @Override
    public void updateProductIncome(ProductIncome productIncome) throws DataAccessException {
        try {
            update(productIncome.getId(), productIncome);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not update ProductIncome"){};
        }
    }


}
