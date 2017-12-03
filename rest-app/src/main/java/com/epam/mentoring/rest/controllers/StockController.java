package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class.getName());

    private ProductService productService;

    public StockController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public ResponseEntity<List<ProductWithQuantityView>> productWithQuantityViewList() {
        logger.info("GET /stock: getting products with quantities list");
        List<ProductWithQuantityView> allProductsWithQuantitiesViews = productService.getAllProductsWithQuantitiesViews();
        ResponseEntity<List<ProductWithQuantityView>> responseEntity = new
                ResponseEntity<>(allProductsWithQuantitiesViews, HttpStatus.OK);
        return responseEntity;
    }



}
