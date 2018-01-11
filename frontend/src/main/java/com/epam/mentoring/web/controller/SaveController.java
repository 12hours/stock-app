package com.epam.mentoring.web.controller;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.web.client.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/add")
public class SaveController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductServiceClient productServiceClient;

    @GetMapping("/")
    public String saveProductForm(){
        return "new_income";
    }

    @PostMapping("/product")
    public String saveProduct(@ModelAttribute("productForm") ProductForm productForm) {

        return "index";
    }

}
