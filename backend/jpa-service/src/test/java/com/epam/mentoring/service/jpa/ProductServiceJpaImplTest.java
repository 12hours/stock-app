package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceJpaImplTest {

    private EntityManagerFactory emf;

    private ProductService service;

    @Before
    public void before() {
        emf = Persistence.createEntityManagerFactory("PU");
        service = new ProductServiceJpaImpl(emf);
    }

    @After
    public void after() {
        emf.close();
    }

    @Test
    public void saveProductTest() {
        Product product = new Product(null, "TestProduct", BigDecimal.ONE, new ProductType(1, null));
        Integer id = service.saveProduct(product);

        Product productById = service.findProductById(id);
        assertEquals(product.getName(), productById.getName());
        assertEquals(0, product.getPrice().compareTo(productById.getPrice()));
        assertEquals(product.getType().getId(), productById.getType().getId());
    }

    @Test
    public void saveProductFormTest() {
        ProductForm productForm = new ProductForm("TestProduct", BigDecimal.TEN, 2);
        Integer id = service.saveProduct(productForm);

        Product productById = service.findProductById(id);
        assertEquals(productForm.getName(), productById.getName());
        assertEquals(0, productForm.getPrice().compareTo(productById.getPrice()));
        assertEquals(productForm.getProductTypeId(), productById.getType().getId());
    }

    @Test
    public void updateProductTest() {
        Product product = service.findProductById(3);
        product.setName("NewProductName");
        product.setPrice(BigDecimal.valueOf(587));
        service.updateProduct(product);

        Product productById = service.findProductById(3);
        assertEquals(product.getName(), productById.getName());
        assertEquals(0, product.getPrice().compareTo(productById.getPrice()));
    }

    @Test
    public void updateProductTypeTest() {
        Product product = service.findProductById(3);
        assertEquals(Integer.valueOf(3), product.getType().getId());
        product.getType().setId(1);

        service.updateProduct(product);
        Product productById = service.findProductById(3);
        assertEquals(Integer.valueOf(1), productById.getType().getId());
    }

    @Test
    public void updateProductFormTest() {
        ProductForm productForm = new ProductForm("NewProductName", BigDecimal.valueOf(439), 1);
        service.updateProduct(3, productForm);

        Product productById = service.findProductById(3);
        assertEquals(Integer.valueOf(1), productById.getType().getId());
        assertEquals(productForm.getName(), productById.getName());
        assertEquals(0, productForm.getPrice().compareTo(productById.getPrice()));
    }

    @Test
    public void getAllProductsTest() {
        List<Product> products = service.getAllProducts();
        assertNotNull(products);
        assertEquals(9, products.size());
    }

    @Test
    public void deleteProductTest() {
        assertNotNull(service.findProductById(3));
        service.deleteProductById(3);
        assertNull(service.findProductById(3));
    }

    @Test
    public void getProductsWithQuantitesTest() {
        List<ProductWithQuantityView> allProductsWithQuantitiesViews = service.getAllProductsWithQuantitiesViews();
        assertNotNull(allProductsWithQuantitiesViews);

        List<ProductWithQuantityView> expectedQuantitiesList = new ArrayList<ProductWithQuantityView>() {
            {
                add(new ProductWithQuantityView(new ProductView(1, "Intel Core i7 8700",    new BigDecimal("360.00"), 1),  10));
                add(new ProductWithQuantityView(new ProductView(2, "Intel Core i3 8100",    new BigDecimal("135.00"), 1),  40));
                add(new ProductWithQuantityView(new ProductView(3, "Nvidia GTX 1050Ti",     new BigDecimal("200.00"), 3),  45));
                add(new ProductWithQuantityView(new ProductView(4, "Intel Pentium G4360",   new BigDecimal("65.00"), 1), 50));
                add(new ProductWithQuantityView(new ProductView(5, "AMD Ryzen 7 1700",      new BigDecimal("325.00"), 1),  25));
                add(new ProductWithQuantityView(new ProductView(6, "Samsung 850 Evo 256 Gb",new BigDecimal("100.00"), 4), 50));
                add(new ProductWithQuantityView(new ProductView(7, "Intel Core i5 6600K",   new BigDecimal("250.00"), 1), 10));
                add(new ProductWithQuantityView(new ProductView(8, "Kingston UV400 120 Gb", new BigDecimal("65.00"), 4),  30));
                add(new ProductWithQuantityView(new ProductView(9, "ASRock Z370",           new BigDecimal("165.00"), 2), 25));
            }
        };

        assertTrue(allProductsWithQuantitiesViews.containsAll(expectedQuantitiesList));
    }


    @Test
    public void getSingleProductWithQuantityTest() {
        ProductWithQuantityView expectedProductWithQuantityView = new ProductWithQuantityView(new ProductView(3, "Nvidia GTX 1050Ti", new BigDecimal("200.00"), 3), 45);
        ProductWithQuantityView actualProductWithQuantity = service.getProductWithQuantity(3);
        assertEquals(expectedProductWithQuantityView, actualProductWithQuantity);
    }

    @Test
    public void getProductOfProductIncomeTest() {
        Product expectedProduct = service.findProductById(3);
        Product actualProduct = service.getProductOfProductIncome(5);
        assertEquals(expectedProduct, actualProduct );
    }
}