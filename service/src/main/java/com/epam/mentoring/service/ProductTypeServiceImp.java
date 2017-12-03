package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ProductTypeDao;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

public class ProductTypeServiceImp implements ProductTypeService {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeServiceImp.class.getName());

    ProductTypeDao productTypeDao;

    public ProductTypeServiceImp(ProductTypeDao productTypeDao) {
        this.productTypeDao = productTypeDao;
    }

    @Override
    public ProductType getProductTypeById(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id provided for ProductType");
        logger.debug("Getting ProductType with id "+ id);
        return productTypeDao.getProductTypeById(id);
    }

    @Override
    public List<ProductType> getAllProductTypes() throws DataAccessException {
        List<ProductType> productTypes = productTypeDao.getAllProductTypes();
        logger.debug("Getting all ProductTypes. Found " +productTypes.size() + " items");
        return productTypes;
    }

    @Override
    public Integer updateProductType(ProductType productType) throws DataAccessException {
        return 0;
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
    public int deleteProductType(Integer id) throws DataAccessException {
        return 0;
    }
}
