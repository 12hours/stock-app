package com.epam.mentoring.web.controllers;

import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.client.IProductIncomeConsumer;
import com.epam.mentoring.client.IProductTypeConsumer;
import com.epam.mentoring.client.ISupplierConsumer;
import com.epam.mentoring.data.model.*;
import com.epam.mentoring.web.DTOUtils;
import com.epam.mentoring.web.forms.ProductIncomeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public void saveProductIncome(@ModelAttribute ProductIncomeForm form) {
        ProductIncome productIncome = DTOUtils.map(form);
        productIncomeConsumer.addProductIncome(productIncome);
    }
}
