package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductTypeController {

    private ProductTypeService productTypeService;

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class.getName());

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping(value = Constants.URI_API_PREFIX + Constants.URI_PRODUCT_TYPE)
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        logger.debug("GET /product-type");
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<ProductType>> responseEntity =
                new ResponseEntity<>(productTypes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = Constants.URI_API_PREFIX + Constants.URI_PRODUCT_TYPE)
    public ResponseEntity<Void> addProductType(@Valid @RequestBody ProductTypeForm productTypeForm) {
        try {
            logger.debug("trying to add new product type: " + productTypeForm.toString());
            Integer integer = productTypeService.saveProductType(productTypeForm);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            logger.debug("can not add new product type: " + ex.getMessage());
            throw new CannotSaveException();
        }
    }

}
