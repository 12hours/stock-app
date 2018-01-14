package com.epam.mentoring.web.controller;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.web.client.ProductIncomeServiceClient;
import com.epam.mentoring.web.client.ProductServiceClient;
import com.epam.mentoring.web.client.ProductTypeServiceClient;
import com.epam.mentoring.web.client.SupplierServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/add")
public class SaveController {

    @Autowired
    ProductServiceClient productServiceClient;

    @Autowired
    ProductTypeServiceClient productTypeServiceClient;

    @Autowired
    ProductIncomeServiceClient productIncomeServiceClient;

    @Autowired
    SupplierServiceClient supplierServiceClient;

    @GetMapping("/")
    public String saveProductForm() {
        return "new_income";
    }

    @PostMapping(value = "/product")
    public String saveProduct(@RequestParam("name") String name,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("productTypeId") Integer productTypeId) {
        ProductForm productForm = new ProductForm(name, price, productTypeId);
        Integer id = productServiceClient.saveProduct(productForm);

        return "index";
    }

    @PostMapping(value = "/type")
    public String saveProductType(@RequestParam("name") String name) {
        ProductTypeForm productTypeForm = new ProductTypeForm(name);
        Integer id = productTypeServiceClient.saveProductType(productTypeForm);

        return "index";
    }

    @PostMapping(value = "/income")
    public String saveProductIncome(@RequestParam("orderNumber") Long orderNumber,
                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                    @RequestParam("quantity") Integer quantity,
                                    @RequestParam("productId") Integer productId,
                                    @RequestParam("supplierId") Integer supplierId,
                                    @RequestParam("userId") Integer userId) {
        ProductIncomeForm productIncomeForm = new ProductIncomeForm(orderNumber, date, quantity, productId, supplierId, userId);
        Integer id = productIncomeServiceClient.saveProductIncome(productIncomeForm);

        return "index";
    }

    @PostMapping(value = "/supplier")
    public String saveSupplier(@RequestParam("name") String name,
                               @RequestParam("details") String details) {
        SupplierForm supplierForm = new SupplierForm(name, details);
        Integer id = supplierServiceClient.saveSupplier(supplierForm);

        return "index";
    }

}
