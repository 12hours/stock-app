package com.epam.mentoring.client.direct;

import com.epam.mentoring.client.ProductTypeConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.service.IProductTypeService;
import org.springframework.util.Assert;

import java.util.List;

public class DirectProductTypeConsumer implements ProductTypeConsumer {

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
    public Integer saveProductType(ProductTypeForm productTypeForm) throws ServerDataAccessException {
        return null;
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
