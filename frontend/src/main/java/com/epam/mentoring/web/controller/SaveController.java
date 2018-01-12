package com.epam.mentoring.web.controller;

import com.epam.mentoring.web.client.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

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

    @PostMapping(value = "/product")
    public String saveProduct(@RequestParam("name") String string,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("productTypeId") Integer productTypeId) {


        return "index";
    }

}
