package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import com.epam.mentoring.service.jpa.dao.AbstractProductDao;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collection;
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
            throw new DataAccessException("Can not get Product", e) {
            };
        }
    }

    @Override
    public Integer saveProduct(Product product) throws DataAccessException {
        try {
            persist(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save Product", e) {
            };
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
            throw new DataAccessException("Can not update Product", e) {
            };
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
            throw new DataAccessException("Can not get list of Products", e) {
            };
        }
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException {
        try {
            return getAllProductsWithProductIncomesMap();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not fetch list of products with quantities") {
            };
        }
    }

    @Override
    public void deleteProductById(Integer id) throws DataAccessException {
        try {
            remove(id);
        } catch (ObjectNotFoundException e) {
            throw new DataRetrievalFailureException("Object with given id does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not delete Product", e) {
            };
        }
    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws DataAccessException {
        Map<Product, Integer> allProductsWithProductIncomesMap = getAllProductsWithQuantities();
        List<ProductWithQuantityView> productWithQuantityViewsList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : allProductsWithProductIncomesMap.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            productWithQuantityViewsList.add(new ProductWithQuantityView(product.getId(), product.getName(), quantity));
        }
        return productWithQuantityViewsList;
    }

    @Override
    public Collection<ProductIncome> getIncomesOfProduct(Integer id) throws DataAccessException {
        Collection<ProductIncome> productIncomes = null;
        try {
            productIncomes = (Collection<ProductIncome>) findAndFetchField(id, "productIncomes");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get access to productIncomes field of Product", e) {};
        }
        return productIncomes;
    }


}
