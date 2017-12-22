package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ProductDao;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.test.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao dao;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;

    private ProductService productService;

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
        when(dao.getAllProductsWithQuantities()).thenReturn(new HashMap<Product, Integer>() {
            {
                put(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")), 15);
                put(new Product(2, "Core i5", new BigDecimal(200.0), new ProductType(1, "CPU")), 20);
                put(new Product(3, "Core i7", new BigDecimal(300.0), new ProductType(1, "CPU")), 25);
            }
        });

        productService = new ProductServiceImpl(dao);
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
        assertEquals(Integer.valueOf(15),
                products.get(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU"))));
    }

    @Test
    public void deleteProductTets() {
        productService.deleteProductById(3);
        verify(dao, times(1)).deleteProduct(anyInt());
        verify(dao).deleteProduct(integerArgumentCaptor.capture());
        assertEquals(3, integerArgumentCaptor.getValue().intValue());
    }

    @Test
    public void updateProductTest() {
        ProductForm productForm = DTOUtils.map(TestData.products().get(0), ProductForm.class);
        productService.updateProduct(productForm);
        verify(dao, times(1)).updateProduct(any(Product.class));
        verify(dao).updateProduct(productArgumentCaptor.capture());
    }
}