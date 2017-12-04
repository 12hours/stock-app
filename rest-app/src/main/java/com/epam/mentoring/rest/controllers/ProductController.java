package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.ProductService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = Constants.URI_API_PREFIX + Constants.URI_PRODUCT)
    public ResponseEntity<List<ProductView>> getAllProducts() throws DataAccessException {
        log.debug("Getting list of products");
        try {
            List<ProductView> productViewsList = productService.getAllProductsAsViews();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<List<ProductView>> responseEntity =
                    new ResponseEntity<>(productViewsList, httpHeaders, HttpStatus.OK);
            return responseEntity;
        } catch (DataAccessException ex) {
            log.error("Can not get list of products: {}", ex);
            throw ex;
        }
    }

    @PostMapping(value = Constants.URI_API_PREFIX + Constants.URI_PRODUCT)
    public ResponseEntity<Void> saveProduct(@Valid @RequestBody ProductForm productForm) throws CannotSaveException {
        log.debug("Saving product: {}", productForm.toString());
        try {
            Integer id = productService.saveProduct(productForm);
            return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/product/" + id.toString())).build();
        } catch (DataAccessException ex) {
            log.error("Can not save product: {}", ex);
            throw new CannotSaveException("Can not save product", ex);
        }
        // It appears it is wrong way to set location header
//        ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(HttpStatus.CREATED);
//        responseEntity.getHeaders().setLocation(URI.create("/product/" + id.toString()));
//        return responseEntity;
        //
    }


}
