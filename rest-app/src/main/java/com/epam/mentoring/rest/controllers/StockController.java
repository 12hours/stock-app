package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.IProductIncomeService;
import com.epam.mentoring.service.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
public class StockController {

    private IProductService productService;

    public StockController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductWithQuantityView>> productWithQuantityViewList() {
        List<ProductWithQuantityView> allProductsWithQuantitiesViews = productService.getAllProductsWithQuantitiesViews();
        ResponseEntity<List<ProductWithQuantityView>> responseEntity = new
                ResponseEntity<>(allProductsWithQuantitiesViews, HttpStatus.OK);
        return responseEntity;
    }



}
