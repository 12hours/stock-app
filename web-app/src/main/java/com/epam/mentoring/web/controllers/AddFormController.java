package com.epam.mentoring.web.controllers;

import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.client.IProductIncomeConsumer;
import com.epam.mentoring.client.IProductTypeConsumer;
import com.epam.mentoring.client.ISupplierConsumer;
import com.epam.mentoring.data.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/add")
public class AddFormController {

    private IProductIncomeConsumer productIncomeConsumer;
    private IProductConsumer productConsumer;
    private IProductTypeConsumer productTypeConsumer;
    private ISupplierConsumer supplierConsumer;

    @Autowired
    public AddFormController(IProductIncomeConsumer productIncomeConsumer, IProductConsumer productConsumer, IProductTypeConsumer productTypeConsumer, ISupplierConsumer supplierConsumer) {
        this.productIncomeConsumer = productIncomeConsumer;
        this.productConsumer = productConsumer;
        this.productTypeConsumer = productTypeConsumer;
        this.supplierConsumer = supplierConsumer;
    }


    @GetMapping("/product-income")
    public String getAddProductIncomeForm(Model model) {
        List<Product> products = productConsumer.getAllProducts();
        List<ProductType> productTypes = productTypeConsumer.findAll();
        List<Supplier> suppliers = supplierConsumer.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("suppliers", suppliers);
        return "add-product-income";
    }

    @PostMapping("/product-income")
    public void saveProductIncome(@RequestParam("productId") Integer productId,
                                  @RequestParam("quantity") Integer quantity,
                                  @RequestParam("orderNumber") Long orderNumber,
                                  @RequestParam("date") Date date,
                                  @RequestParam("supplierId") Integer supplierId,
                                  @RequestParam("userId") Integer userId) {
        ProductIncome productIncome = new ProductIncome();
        Product product = new Product();
        ProductType productType = new ProductType();
        Supplier supplier = new Supplier();
        User user = new User();
        productIncomeConsumer.addProductIncome(productIncome);
    }
}
