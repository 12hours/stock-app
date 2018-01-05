package com.epam.mentoring.test;

import com.epam.mentoring.data.model.*;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestData {

    public static List<Product> products() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")));
        products.add(new Product(2, "Core i5", new BigDecimal(200.0), new ProductType(1, "CPU")));
        products.add(new Product(3, "Core i7", new BigDecimal(300.0), new ProductType(1, "CPU")));
        return products;
    }

    public static List<ProductView> productViews() {
        List<ProductView> productViews = new ArrayList<>();
        products().forEach(product -> productViews.add(DTOUtils.map(product, ProductView.class)));
        return productViews;
    }

    public static List<ProductType> productTypes() {
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(new ProductType(1, "CPU"));
        productTypes.add(new ProductType(2, "Motherboard"));
        productTypes.add(new ProductType(3, "Videocard"));

        return productTypes;
    }

    public static List<Supplier> suppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(0, "Computer univers", "Jasper, TX"));
        suppliers.add(new Supplier(1, "Computer planet", "LA, CA"));
        return suppliers;
    }

    public static List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", "12345", false));
        users.add(new User(2, "Bob", "54321", true));
        return users;
    }

    public static List<ProductIncome> productIncomes() {
        List<Product> products = products();
        List<User> users = users();
        List<Supplier> suppliers = suppliers();
        List<ProductIncome> productIncomes = new ArrayList<>();
        productIncomes.add(
                new ProductIncome(1, 10, 10010L,
                        new Date(System.currentTimeMillis()), products.get(1),
                        users.get(0),
                        suppliers.get(0)));
        productIncomes.add(
                new ProductIncome(2, 20, 10010L,
                        new Date(System.currentTimeMillis()), products.get(2),
                        users.get(0),
                        suppliers.get(0)));
        return productIncomes;
    }

    public static List<ProductIncomeForm> productIncomeForms(){
        List<ProductIncome> productIncomes = productIncomes();
        List<ProductIncomeForm> productIncomeForms = new ArrayList<>();
        for (ProductIncome productIncome : productIncomes) {
            productIncomeForms.add(DTOUtils.map(productIncome, ProductIncomeForm.class));
        }
        return productIncomeForms;
    }

    public static List<ProductWithQuantityView> productWithQuantityViews() {
        List<Product> products = products();
        ArrayList<ProductWithQuantityView> productWithQuantityViews = new ArrayList<ProductWithQuantityView>(){
            {
                add(new ProductWithQuantityView(products.get(0).getId(), products.get(0).getName(), 10));
                add(new ProductWithQuantityView(products.get(1).getId(), products.get(1).getName(), 20));
                add(new ProductWithQuantityView(products.get(2).getId(), products.get(2).getName(), 30));
            }
        };
        return productWithQuantityViews;
    }

}
