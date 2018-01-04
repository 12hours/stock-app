package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.service.ProductTypeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ProductTypeServiceJpaTest {

    private EntityManagerFactory emf;

    private ProductTypeService service;

    @Before
    public void before() {
        emf = Persistence.createEntityManagerFactory("PU");
        service = new ProductTypeServiceJpa(emf);
    }

    @After
    public void after() {
        emf.close();
    }

    @Test
    public void getProductTypeTest() {
        ProductType productTypeById = service.getProductTypeById(1);
        assertEquals(Integer.valueOf(1), productTypeById.getId()); // look at init.sql
        assertEquals("CPU", productTypeById.getName());
    }

    @Test
    public void saveAndGetProductTypeTest() {
        ProductType productType = new ProductType(null, "TEST_TYPE");
        Integer id = service.saveProductType(productType);

        ProductType productTypeById = service.getProductTypeById(id);
        assertEquals(productType.getName(), productTypeById.getName());
    }

    @Test
    public void deleteProductTypeTest() {
        ProductType productType = new ProductType(null, "TEST_TYPE");
        Integer id = service.saveProductType(productType);

        service.deleteProductType(id);
        ProductType productTypeById = service.getProductTypeById(id);
        assertNull(productTypeById);
    }

    @Test
    public void updateProductTypeTest(){
        ProductType productToUpdate = service.getProductTypeById(3);
        productToUpdate.setName("UPDATED_NAME");
        service.updateProductType(productToUpdate);

        ProductType productTypeById = service.getProductTypeById(3);
        assertEquals("UPDATED_NAME", productTypeById.getName());
    }

    @Test
    public void updateProductTypeAssertCollectionFieldIsNotUpdatedTest(){
        ProductType productToUpdate = service.getProductTypeById(3);
        productToUpdate.setName("UPDATED_NAME");
        HashSet<Product> products = new HashSet<Product>() {
            {
                add(new Product(1, "TestProduct", BigDecimal.ONE, productToUpdate));
            }
        };
        productToUpdate.setProducts(products);
        service.updateProductType(productToUpdate);
        ProductType productTypeById = emf.createEntityManager().find(ProductType.class, 3);
        assertEquals("UPDATED_NAME", productTypeById.getName());
        assertNotEquals(products, productTypeById.getProducts());
        System.out.println(products);
        System.out.println(productTypeById.getProducts());
    }


    @Test
    public void getProductCollectionTest() {
        Collection<Product> allProductsOfType = service.getAllProductsOfType(1);
        assertEquals(5, allProductsOfType.size());

    }

    @Test
    public void addNewProductToTypeTest() {
        Product product = new Product(42, "TestProduct", BigDecimal.ONE, null);
        service.addProductToProductType(3, product);

        Collection<Product> allProductsOfType = service.getAllProductsOfType(3);
        assertTrue(allProductsOfType.contains(product));
    }

}