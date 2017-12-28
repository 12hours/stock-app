package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.service.ProductTypeService;
import com.epam.mentoring.service.jpa.config.EntityManagerFactoryWrapper;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductTypeServiceJpa extends AbstractDao<ProductType> implements ProductTypeService {

    public ProductTypeServiceJpa() {
        super(ProductType.class);
    }

    @Override
    public ProductType getProductTypeById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public List<ProductType> getAllProductTypes() throws DataAccessException {
        return null;
    }

    @Override
    public void updateProductType(ProductType productType) throws DataAccessException {

    }

    @Override
    public Integer saveProductType(ProductType productType) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveProductType(ProductTypeForm productTypeForm) throws DataAccessException {
        return null;
    }

    @Override
    public int deleteProductType(Integer id) throws DataAccessException {
        return 0;
    }
}
