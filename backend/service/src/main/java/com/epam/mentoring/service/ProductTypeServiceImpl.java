package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ProductTypeDao;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class.getName());

    private ProductTypeDao productTypeDao;

    public ProductTypeServiceImpl(ProductTypeDao productTypeDao) {
        this.productTypeDao = productTypeDao;
    }

    @Override
    public ProductType getProductTypeById(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id provided for ProductType");
        logger.debug("Getting ProductType with id "+ id);
        try {
            ProductType productType = productTypeDao.getProductTypeById(id);
            return productType;
        } catch (EmptyResultDataAccessException e) {
            logger.debug("No object found");
            return null;
        }
    }

    @Override
    public List<ProductType> getAllProductTypes() throws DataAccessException {
        List<ProductType> productTypes = productTypeDao.getAllProductTypes();
        logger.debug("Getting all ProductTypes. Found " +productTypes.size() + " items");
        return productTypes;
    }

    @Override
    public void updateProductType(ProductType productType) throws DataAccessException {
        Assert.notNull(productType, "No ProductType provided for saving");
        Assert.notNull(productType.getId(), "No ProductType id for saving");
        Assert.notNull(productType.getName(), "No name for product type provided");
        logger.debug("Updating ProductType with id: {}", productType.getId());
        productTypeDao.updateProductType(productType);
    }

    @Override
    public Integer saveProductType(ProductType productType) throws DataAccessException {
        Assert.notNull(productType, "No ProductType provided for saving");
        Assert.notNull(productType.getName(), "No name for product type provided");
        logger.debug("Saving ProductType " + productType.toString());
        return productTypeDao.addProductType(productType);
    }

    @Override
    public Integer saveProductType(ProductTypeForm productTypeForm) throws DataAccessException {
        ProductType productType = DTOUtils.map(productTypeForm, ProductType.class);
        return saveProductType(productType);
    }

    @Override
    public void deleteProductType(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id provided");
        logger.debug("Deleting item with id: {}", id);
        productTypeDao.deleteProductType(id);
        return 0;
    }
}
