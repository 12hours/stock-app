package com.epam.mentoring.web.controllers;

import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.client.IProductIncomeConsumer;
import com.epam.mentoring.client.IProductTypeConsumer;
import com.epam.mentoring.client.ISupplierConsumer;
import com.epam.mentoring.data.model.*;
import com.epam.mentoring.web.DTOUtils;
import com.epam.mentoring.web.forms.ProductForm;
import com.epam.mentoring.web.forms.ProductIncomeForm;
import com.epam.mentoring.web.forms.ProductTypeForm;
import com.epam.mentoring.web.forms.SupplierForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/add")
//@SessionAttributes("productIncomeForm")
public class AddFormController {

    @Autowired
    private IProductIncomeConsumer productIncomeConsumer;

    @Autowired
    private IProductConsumer productConsumer;

    @Autowired
    private IProductTypeConsumer productTypeConsumer;

    @Autowired
    private ISupplierConsumer supplierConsumer;

//    @Autowired
//    public AddFormController(IProductIncomeConsumer productIncomeConsumer, IProductConsumer productConsumer, IProductTypeConsumer productTypeConsumer, ISupplierConsumer supplierConsumer) {
//        this.productIncomeConsumer = productIncomeConsumer;
//        this.productConsumer = productConsumer;
//        this.productTypeConsumer = productTypeConsumer;
//        this.supplierConsumer = supplierConsumer;
//    }

    @GetMapping("/")
    public String root() {
        return "redirect:/add-product-income";
    }

    @GetMapping("/add-product-income")
    public String addProductIncomeForm(Model model) {
        List<Product> products = productConsumer.getAllProducts();
        List<ProductType> productTypes = productTypeConsumer.findAll();
        List<Supplier> suppliers = supplierConsumer.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("suppliers", suppliers);
        return "add-product-income";
    }

    @PostMapping(value = "/add-product-income")
    public String saveProductIncome(@ModelAttribute("productIncomeForm") ProductIncomeForm productIncomeForm,
                                    Errors errors) {
        ProductIncome productIncome = DTOUtils.map(productIncomeForm);
        productIncomeConsumer.addProductIncome(productIncome);
        return "redirect:/stock";
    }

    @GetMapping("/add-product-type")
    public String addProductTypeForm(Model model) {

        return "add-product-type";
    }

    @PostMapping("/add-product-type")
    public String addProductType(@ModelAttribute("productTypeForm") ProductTypeForm productTypeForm) {
        ProductType productType = DTOUtils.map(productTypeForm, ProductType.class);
        productTypeConsumer.saveProductType(productType);
        return "redirect:/add";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        List<ProductType> productTypes = productTypeConsumer.findAll();
        model.addAttribute("productTypes", productTypes);
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("productForm") ProductForm productForm, Errors errors) {
        Product product = DTOUtils.map(productForm, Product.class);
        productConsumer.saveProduct(product);
        return "redirect:/add";
    }

    @GetMapping("/add-supplier")
    public String addSupplierForm(Model model) {

        return "add-supplier";
    }

    @PostMapping("/add-supplier")
    public String addSupplier(@ModelAttribute("supplierForm") SupplierForm supplierForm, Errors errors) {
        Supplier supplier = DTOUtils.map(supplierForm, Supplier.class);
        supplierConsumer.saveSupplier(supplier);
        return "redirect:/add";
    }
}
