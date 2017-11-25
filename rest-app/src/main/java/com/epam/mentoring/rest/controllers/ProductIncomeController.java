package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.IProductIncomeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//@RestController
@RequestMapping("/income")
public class ProductIncomeController {

    private IProductIncomeService productIncomeService;

    public ProductIncomeController(IProductIncomeService productIncomeService) {
        this.productIncomeService = productIncomeService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> addIncome(@RequestBody ProductIncomeForm productIncomeForm,
                                          UriComponentsBuilder ucBuilder) {
        Integer id = productIncomeService.saveProductIncome(productIncomeForm);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/income/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
