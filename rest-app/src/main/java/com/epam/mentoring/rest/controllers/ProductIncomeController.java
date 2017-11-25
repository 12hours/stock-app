package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.IProductIncomeService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
//@RequestMapping("/income")
public class ProductIncomeController {

    private IProductIncomeService productIncomeService;

    public ProductIncomeController(IProductIncomeService productIncomeService) {
        this.productIncomeService = productIncomeService;
    }

    @PostMapping(value = "/income", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addIncome(@Valid @RequestBody ProductIncomeForm productIncomeForm,
                                          UriComponentsBuilder ucBuilder) throws CannotSaveException {
        try {
            Integer id = productIncomeService.saveProductIncome(productIncomeForm);
            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(ucBuilder.path("/income/{id}").buildAndExpand(id).toUri());
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            throw new CannotSaveException();
        }
    }
}
