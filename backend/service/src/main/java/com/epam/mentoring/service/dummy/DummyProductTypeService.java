package com.epam.mentoring.service.dummy;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.service.ProductTypeService;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class DummyProductTypeService implements ProductTypeService {
    @Override
    public ProductType getProductTypeById(Integer id) throws DataAccessException {
        return new ProductType(1, "CPU");
    }

    @Override
    public List<ProductType> getAllProductTypes() throws DataAccessException {
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(new ProductType(1, "CPU"));
        productTypes.add(new ProductType(2, "Motherboard"));
        productTypes.add(new ProductType(3, "Videocard"));

        return productTypes;
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
        return 14;
    }

    @Override
    public void deleteProductType(Integer id) throws DataAccessException {
        return 0;
    }
}
