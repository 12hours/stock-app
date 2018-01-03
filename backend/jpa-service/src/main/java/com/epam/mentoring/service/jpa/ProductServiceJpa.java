package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

public class ProductServiceJpa extends AbstractDao<Product> implements ProductService {

    public ProductServiceJpa(EntityManagerFactory emf) {
        super(Product.class, emf);
    }

    @Override
    public Product findProductById(Integer id) throws DataAccessException {
        Product product = find(id);
        return product;
    }

    @Override
    public Integer saveProduct(Product product) throws DataAccessException {
        try {
            persist(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save Product", e){};
        }
        return product.getId();
    }

    @Override
    public Integer saveProduct(ProductForm productForm) throws DataAccessException {
        Product product = DTOUtils.map(productForm, Product.class);
        return saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws DataAccessException {

    }

    @Override
    public void updateProduct(Integer id, ProductForm productForm) throws DataAccessException {
        Product product = DTOUtils.map(productForm, Product.class);
        update(id, product);
    }

    @Override
    public List<Product> getAllProducts() throws DataAccessException {
        List<Product> products = findAll();
        return products;
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) throws DataAccessException {
        remove(id);
    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() {
        return null;
    }
}
