package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import com.epam.mentoring.service.jpa.dao.AbstractProductDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

public class ProductServiceJpaImpl extends AbstractProductDao implements ProductService {

    public ProductServiceJpaImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Product findProductById(Integer id) throws DataAccessException {
        try {
            Product product = find(id);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get Product", e) {};
        }
    }

    @Override
    public Integer saveProduct(Product product) throws DataAccessException {
        try {
            persist(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save Product", e) {};
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
        try {
            update(product.getId(), product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not update Product", e) {};
        }
    }

    @Override
    public void updateProduct(Integer id, ProductForm productForm) throws DataAccessException {
        Product product = DTOUtils.map(productForm, Product.class);
        product.setId(id);
        update(id, product);
    }

    @Override
    public List<Product> getAllProducts() throws DataAccessException {
        try {
            List<Product> products = findAll();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get list of Products", e) {};
        }
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) throws DataAccessException {
        try {
            remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not delete Product", e) {};
        }
    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() {
        getAllProductsWithProductIncomesMap();
        return null;
    }
}
