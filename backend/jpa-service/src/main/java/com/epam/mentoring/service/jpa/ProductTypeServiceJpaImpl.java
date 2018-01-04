package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.service.ProductTypeService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.List;

public class ProductTypeServiceJpaImpl extends AbstractDao<ProductType> implements ProductTypeService {

    public ProductTypeServiceJpaImpl(EntityManagerFactory emf) {
        super(ProductType.class, emf);
    }

    @Override
    public ProductType getProductTypeById(Integer id) throws DataAccessException {
        ProductType productType = find(id);
        return productType;
    }

    @Override
    public List<ProductType> getAllProductTypes() throws DataAccessException {
        return findAll();
    }

    @Override
    public void updateProductType(ProductType productType) throws DataAccessException {
        update(productType.getId(), productType);
    }

    @Override
    public Integer saveProductType(ProductType productType) throws DataAccessException {
        try {
            persist(productType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save ProductType", e) {};
        }
        return productType.getId();
    }

    @Override
    public Integer saveProductType(ProductTypeForm productTypeForm) throws DataAccessException {
        ProductType productType = DTOUtils.map(productTypeForm, ProductType.class);
        return saveProductType(productType);
    }

    @Override
    public void deleteProductType(Integer id) throws DataAccessException {
        remove(id);
    }

    @Override
    public Collection<Product> getAllProductsOfType(Integer id) {
        Collection<Product> products = null;
        try {
            products = (Collection<Product>) findAndFetchField(id, "products");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get access to products field of ProductType", e) {};
        }
        return products;
    }

    @Override
    public void addProductToProductType(Integer id, Product product) {
        try {
            ProductType productType = find(id, "products");
            product.setType(productType);
            productType.getProducts().add(product);
            merge(productType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not add product to ProductType", e) {};
        }
    }
}
