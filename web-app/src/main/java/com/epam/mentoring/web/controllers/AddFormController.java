package com.epam.mentoring.web.controllers;

import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.client.ProductIncomeConsumer;
import com.epam.mentoring.client.ProductTypeConsumer;
import com.epam.mentoring.client.SupplierConsumer;
import com.epam.mentoring.data.model.*;
import com.epam.mentoring.data.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/add")
//@SessionAttributes("productIncomeForm")
public class AddFormController {

    @Autowired
    private ProductIncomeConsumer productIncomeConsumer;

    @Autowired
    private ProductConsumer productConsumer;

    @Autowired
    private ProductTypeConsumer productTypeConsumer;

    @Autowired
    private SupplierConsumer supplierConsumer;

//    @Autowired
//    public AddFormController(ProductIncomeConsumer productIncomeConsumer, ProductConsumer productConsumer, ProductTypeConsumer productTypeConsumer, SupplierConsumer supplierConsumer) {
//        this.productIncomeConsumer = productIncomeConsumer;
//        this.productConsumer = productConsumer;
//        this.productTypeConsumer = productTypeConsumer;
//        this.supplierConsumer = supplierConsumer;
//    }

    @GetMapping("/")
    public String root() throws IOException {
        return "redirect:/add/add-product-income";
    }

    @GetMapping("/add-product-income")
    public String addProductIncomeForm(Model model) {
//        List<Product> products = productConsumer.getAllProducts();
        List<ProductView> products = productConsumer.getAllProductViews();
        List<ProductType> productTypes = productTypeConsumer.findAll();
        List<Supplier> suppliers = supplierConsumer.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("productIncomeForm", new ProductIncomeForm());
        return "add-product-income";
    }

    @PostMapping(value = "/add-product-income")
    public String saveProductIncome(@ModelAttribute("productIncomeForm") ProductIncomeForm productIncomeForm,
                                    BindingResult bindingResult,
                                    Errors errors) {
//        ProductIncome productIncome = DTOUtils.map(productIncomeForm, ProductIncome.class);
//        productIncomeConsumer.saveProductIncome(productIncome);
        productIncomeConsumer.saveProductIncome(productIncomeForm);
        return "redirect:/";
    }

    @GetMapping("/add-product-type")
    public String addProductTypeForm(Model model) {
        model.addAttribute("productTypeForm", new ProductTypeForm());
        return "add-product-type";
    }

    @PostMapping("/add-product-type")
    public String addProductType(@ModelAttribute("productTypeForm") ProductTypeForm productTypeForm) {
//        ProductType productType = DTOUtils.map(productTypeForm, ProductType.class);
//        productTypeConsumer.saveProductType(productType);
        productTypeConsumer.saveProductType(productTypeForm);
        return "redirect:/add/add-product";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        List<ProductType> productTypes = productTypeConsumer.findAll();
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("productForm", new ProductForm());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("productForm") ProductForm productForm, Errors errors) {
//        Product product = DTOUtils.map(productForm, Product.class);
//        productConsumer.saveProduct(product);
        productConsumer.saveProduct(productForm);
        return "redirect:/add/";
    }

    @GetMapping("/add-supplier")
    public String addSupplierForm(Model model) {
        model.addAttribute("supplierForm", new SupplierForm());
        return "add-supplier";
    }

    @PostMapping("/add-supplier")
    public String addSupplier(@ModelAttribute("supplierForm") SupplierForm supplierForm,
                              BindingResult bindingResult,
                              Errors errors) {
//        Supplier supplier = DTOUtils.map(supplierForm, Supplier.class);
//        supplierConsumer.saveSupplier(supplier);
        supplierConsumer.saveSupplier(supplierForm);
        return "redirect:/add/";
    }
}
