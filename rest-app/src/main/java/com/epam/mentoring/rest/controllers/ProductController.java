package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.service.ProductService;
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

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductView>> getAllProducts() {
        List<ProductView> productViewsList = productService.getAllProductsAsViews();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<ProductView>> responseEntity =
                new ResponseEntity<>(productViewsList, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/product")
    public ResponseEntity<Void> saveProduct(@Valid @RequestBody ProductForm productForm) {
        Integer id = productService.saveProduct(productForm);
        // It appears it is wrong way to set location header
//        ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(HttpStatus.CREATED);
//        responseEntity.getHeaders().setLocation(URI.create("/product/" + id.toString()));
//        return responseEntity;
        //
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/product/" + id.toString())).build();
    }


}
