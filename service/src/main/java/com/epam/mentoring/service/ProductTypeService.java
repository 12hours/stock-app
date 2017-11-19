package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.IProductTypeDao;
import com.epam.mentoring.data.model.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

public class ProductTypeService implements IProductTypeService {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeService.class.getName());

    IProductTypeDao productTypeDao;

    public ProductTypeService(IProductTypeDao productTypeDao) {
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
    public int updateProductType(ProductType productType) throws DataAccessException {
        return 0;
    }

    @Override
    public int saveProductType(ProductType productType) throws DataAccessException {
        Assert.notNull(productType, "No ProductType provided for saving");
        logger.debug("Saving ProductType " + productType.toString());
        return productTypeDao.addProductType(productType);
    }

    @Override
    public int deleteProductType(Integer id) throws DataAccessException {
        return 0;
    }
}
