package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.IProductDao;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private IProductDao dao;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    private IProductService productService;

    @Before
    public void daoMock() {
        when(dao.getAllProductsWithQuantitesAsViews()).thenReturn(new ArrayList<ProductWithQuantityView>() {
            {
                add(new ProductWithQuantityView(0, "Core i7", 15));
                add(new ProductWithQuantityView(1, "Core i5", 20));
                add(new ProductWithQuantityView(2, "Core i3", 25));
            }
        });
        when(dao.addProduct(any(Product.class))).thenReturn(3);
        when(dao.getAllProducts()).thenReturn(new ArrayList<Product>() {
            {
                add(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")));
                add(new Product(2, "Core i5", new BigDecimal(200.0), new ProductType(1, "CPU")));
                add(new Product(3, "Core i7", new BigDecimal(300.0), new ProductType(1, "CPU")));
            }
        });
        when(dao.getAllProductsWithQuantities()).thenReturn(new HashMap<Product, Integer>(){
            {
                put(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")), 15);
                put(new Product(2, "Core i5", new BigDecimal(200.0), new ProductType(1, "CPU")), 20);
                put(new Product(3, "Core i7", new BigDecimal(300.0), new ProductType(1, "CPU")), 25);
            }
        });
        
        productService = new ProductService(dao);
    }

    @Test
    public void getProductWithQuantityViewListTest() {
        List<ProductWithQuantityView> allProductsWithQuantitiesViews = productService.getAllProductsWithQuantitiesViews();
        assertEquals(3, allProductsWithQuantitiesViews.size());
        assertEquals(new ProductWithQuantityView(0, "Core i7", 15),
                allProductsWithQuantitiesViews.get(0));
    }

    @Test
    public void addProductTest() {
        Product product = Product.builder()
                .name("testProduct")
                .price(BigDecimal.valueOf(100L))
                .type(new ProductType(1, null))
                .build();

        Integer i = productService.saveProduct(product);
        assertEquals(Integer.valueOf(3), i);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductInvalidTest() {
        Product product = new Product();
        productService.saveProduct(product);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductNullTest() {
        Product product = null;
        productService.saveProduct(product);
    }

    @Test
    public void saveProductFormTest() {
        ProductForm productForm = new ProductForm("testProduct", BigDecimal.valueOf(100L), 3);
        Integer id = productService.saveProduct(productForm);
        verify(dao).addProduct(productArgumentCaptor.capture());
        assertEquals(Integer.valueOf(3), id);

        Product expectedProduct = Product.builder()
                .name("testProduct")
                .price(BigDecimal.valueOf(100L))
                .type(new ProductType(3, null))
                .build();
        assertEquals(expectedProduct, productArgumentCaptor.getValue());
    }

    @Test
    public void getAllProductsTest() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(3, products.size());
        assertEquals(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")),
                products.get(0));
    }

    @Test
    public void getAllProductsWithQuantities() {
        Map<Product, Integer> products = productService.getAllProductsWithQuantities();
        assertNotNull(products);
        assertEquals(3, products.size());
        assertEquals( Integer.valueOf(15),
                products.get(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1,"CPU"))));
    }

}