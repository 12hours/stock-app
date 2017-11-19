package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.dao.IProductTypeDao;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.service.IProductTypeService;
import org.springframework.util.Assert;

import java.util.List;

public class DirectProductTypeConsumer implements IProductTypeConsumer {

    private IProductTypeService productTypeService;

    public DirectProductTypeConsumer(IProductTypeService service) {
        this.productTypeService = service;
    }

    @Override
    public Integer saveProductType(ProductType productType) throws ServerDataAccessException {
        Assert.notNull(productType, "No ProductType object provided");
        return productTypeService.saveProductType(productType);
    }

    @Override
    public ProductType findProductType(Integer id) throws ServerDataAccessException {
        Assert.notNull(id, "No id provided");
        return productTypeService.getProductTypeById(id);
    }

    @Override
    public List<ProductType> findAll() throws ServerDataAccessException {
        return productTypeService.getAllProductTypes();
    }
}
