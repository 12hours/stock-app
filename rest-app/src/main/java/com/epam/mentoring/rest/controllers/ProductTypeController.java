package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
public class ProductTypeController {

    private ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping(value = Constants.URI_API_PREFIX + Constants.URI_PRODUCT_TYPE)
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        log.debug("Getting list of product types");
        try {
            List<ProductType> productTypes = productTypeService.getAllProductTypes();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<List<ProductType>> responseEntity =
                    new ResponseEntity<>(productTypes, httpHeaders, HttpStatus.OK);
            return responseEntity;
        } catch (DataAccessException ex) {
            log.error("Can not get list of proudct types: {}", ex);
            throw ex;
        }
    }

    @PostMapping(value = Constants.URI_API_PREFIX + Constants.URI_PRODUCT_TYPE)
    public ResponseEntity<Void> addProductType(@Valid @RequestBody ProductTypeForm productTypeForm) {
        log.debug("Trying to save new product type: " + productTypeForm.toString());
        try {
            Integer integer = productTypeService.saveProductType(productTypeForm);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            log.error("Can not save new product type: {}" + ex);
            throw new CannotSaveException("Can not save new product type", ex);
        }
    }

}
