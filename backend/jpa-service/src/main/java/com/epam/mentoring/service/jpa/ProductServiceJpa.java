package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.jpa.config.HibernateUtil;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class ProductServiceJpa extends AbstractDao<Product> implements ProductService {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public ProductServiceJpa() {
        super(Product.class);
        EntityManager entityManager = hibernateUtil.getEntityManager();
        setEntityManager(entityManager);
        System.out.println("Entity manager: " + entityManager.toString());
    }

    @Override
    public Product findProductById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveProduct(Product product) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveProduct(ProductForm productForm) throws DataAccessException {
        return null;
    }

    @Override
    public void updateProduct(Product product) throws DataAccessException {

    }

    @Override
    public void updateProduct(Integer id, ProductForm productForm) throws DataAccessException {

    }

    @Override
    public List<Product> getAllProducts() throws DataAccessException {
        return null;
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) throws DataAccessException {

    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() {
        return null;
    }
}