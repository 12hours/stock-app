package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StockController {

    private ProductService productService;

    public StockController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = Constants.URI_API_PREFIX + Constants.URI_STOCK)
    public ResponseEntity<List<ProductWithQuantityView>> productWithQuantityViewList() {
        log.debug("Getting products with quantities list");
        try {
            List<ProductWithQuantityView> allProductsWithQuantitiesViews = productService.getAllProductsWithQuantitiesViews();
            ResponseEntity<List<ProductWithQuantityView>> responseEntity = new
                    ResponseEntity<>(allProductsWithQuantitiesViews, HttpStatus.OK);
            return responseEntity;
        } catch (DataAccessException ex) {
            log.error("Can not get products with quantities list");
            throw ex;
        }
    }



}
