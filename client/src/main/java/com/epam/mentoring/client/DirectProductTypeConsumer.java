package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.dao.IProductTypeDao;
import com.epam.mentoring.data.model.ProductType;
import org.springframework.util.Assert;

import java.util.List;

public class DirectProductTypeConsumer implements IProductTypeConsumer {

    private IProductTypeDao dao;

    public DirectProductTypeConsumer(IProductTypeDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer saveProductType(ProductType productType) throws ServerDataAccessException {
        Assert.notNull(productType, "No ProductType object provided");
        return dao.addProductType(productType);
    }

    @Override
    public ProductType findProductType(Integer id) throws ServerDataAccessException {
        Assert.notNull(id, "No id provided");
        return dao.getProductTypeById(id);
    }

    @Override
    public List<ProductType> findAll() throws ServerDataAccessException {
        return dao.getAllProductTypes();
    }
}
