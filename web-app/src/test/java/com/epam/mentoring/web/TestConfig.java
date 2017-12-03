package com.epam.mentoring.web;

import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.client.ProductIncomeConsumer;
import com.epam.mentoring.client.ProductTypeConsumer;
import com.epam.mentoring.client.SupplierConsumer;
import com.epam.mentoring.data.model.*;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.test.TestData;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.math.BigDecimal;
import java.util.*;

@Configuration
public class TestConfig {

    @Bean(name = "refProductList")
    @Scope("prototype")
    List<Product> referencedProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")));
        products.add(new Product(2, "Core i5", new BigDecimal(200.0), new ProductType(1, "CPU")));
        products.add(new Product(3, "Core i7", new BigDecimal(300.0), new ProductType(1, "CPU")));
        return products;
    }

    @Bean(name = "refProductTypeList")
    @Scope("prototype")
    List<ProductType> referencedProductTypeList() {
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(new ProductType(1, "CPU"));
        productTypes.add(new ProductType(2, "Motherboard"));
        productTypes.add(new ProductType(3, "Videocard"));
        return productTypes;
    }

    @Bean(name = "refSupplierList")
    @Scope("prototype")
    List<Supplier> referencedSupplierList() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(0, "Computer univers", "Jasper, TX"));
        suppliers.add(new Supplier(1, "Computer planet", "LA, CA"));
        return suppliers;
    }

    @Bean(name = "refUserList")
    @Scope("prototype")
    List<User> referencedUserList() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", "12345", false));
        users.add(new User(2, "Bob", "54321", true));
        return users;
    }

    @Bean(name = "refProductIncomeList")
    @Scope("prototype")
    List<ProductIncome> referencedProductIncomeList() {

        List<ProductIncome> productIncomes = new ArrayList<>();
        productIncomes.add(
                new ProductIncome(1, 10, 10010L,
                        new Date(System.currentTimeMillis()), referencedProductList().get(1),
                        referencedUserList().get(0),
                        referencedSupplierList().get(0)));
        productIncomes.add(
                new ProductIncome(2, 20, 10010L,
                        new Date(System.currentTimeMillis()), referencedProductList().get(2),
                        referencedUserList().get(0),
                        referencedSupplierList().get(0)));
        return productIncomes;
    }

    @Bean
    ProductIncomeConsumer productIncomeConsumer() {
        ProductIncomeConsumer productIncomeConsumerMock = Mockito.mock(ProductIncomeConsumer.class);
        Mockito.when(productIncomeConsumerMock.saveProductIncome(Mockito.any(ProductIncome.class))).thenReturn(1);
        Mockito.when(productIncomeConsumerMock.findAll()).thenReturn(referencedProductIncomeList());
        Mockito.when(productIncomeConsumerMock.findProductIncome(Mockito.any(Integer.class))).then((Answer<ProductIncome>) invocation -> {
            Integer id = invocation.getArgumentAt(0, Integer.class);
            return referencedProductIncomeList().get(0);
        });
        return productIncomeConsumerMock;
    }

    @Bean
    ProductConsumer productConsumer() {

        List<Product> productsList = referencedProductList();
        List<ProductView> productViews = new ArrayList<>();
        productsList.forEach(product -> productViews.add(DTOUtils.map(product, ProductView.class)));

        ProductConsumer productConsumerMock = Mockito.mock(ProductConsumer.class);
//        Map<Product, Integer> productWithQuantitiesMap = new HashMap<>();
//        productWithQuantitiesMap.put(productsList.get(0), 10);
//        productWithQuantitiesMap.put(productsList.get(1), 20);
//        productWithQuantitiesMap.put(productsList.get(2), 30);
        Mockito.when(productConsumerMock.getAllProductsWithQuantitiesViews()).thenReturn(TestData.productWithQuantityViews());

        Mockito.when(productConsumerMock.getAllProducts()).thenReturn(productsList);
        Mockito.when(productConsumerMock.getAllProductViews()).thenReturn(productViews);
        Mockito.when(productConsumerMock.saveProduct(Mockito.any(Product.class))).thenReturn(4);
//        Mockito.when(productConsumerMock.saveProduct(Mockito.any(Product.class))).then((InvocationOnMock invocation) ->
//        {
//            Product product = invocation.getArgumentAt(0, Product.class);
//            product.setId(4);
//            return product;
//        });

        return productConsumerMock;
    }

    @Bean
    ProductTypeConsumer productTypeConsumer() {
        ProductTypeConsumer productTypeConsumer = Mockito.mock(ProductTypeConsumer.class);
        Mockito.when(productTypeConsumer.findAll()).thenReturn(referencedProductTypeList());
        Mockito.when(productTypeConsumer.findProductType(Mockito.any())).thenAnswer(new Answer<ProductType>() {
            @Override
            public ProductType answer(InvocationOnMock invocation) throws Throwable {
                Integer id = invocation.getArgumentAt(0, Integer.class);
                return referencedProductTypeList().get(id);
            }
        });
        Mockito.when(productTypeConsumer.saveProductType(Mockito.any(ProductType.class))).thenReturn
                (referencedProductTypeList().size());
        return productTypeConsumer;
    }

    @Bean
    SupplierConsumer supplierConsumer() {
        SupplierConsumer supplierConsumer = Mockito.mock(SupplierConsumer.class);
        Mockito.when(supplierConsumer.findAll()).thenReturn(referencedSupplierList());
        Mockito.when(supplierConsumer.saveSupplier(Mockito.any(Supplier.class))).thenReturn(referencedSupplierList().size());
        Mockito.when(supplierConsumer.findSupplier(Mockito.any(Integer.class))).thenAnswer(new Answer<Supplier>() {
            @Override
            public Supplier answer(InvocationOnMock invocation) throws Throwable {
                Integer id = invocation.getArgumentAt(0, Integer.class);
                return referencedSupplierList().get(id);
            }
        });
        return supplierConsumer;
    }

}
