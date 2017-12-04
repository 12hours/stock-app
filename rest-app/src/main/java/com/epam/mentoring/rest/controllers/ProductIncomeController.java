package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.ProductIncomeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
public class ProductIncomeController {


    private ProductIncomeService productIncomeService;

    public ProductIncomeController(ProductIncomeService productIncomeService) {
        this.productIncomeService = productIncomeService;
    }

    @PostMapping(value = Constants.URI_API_PREFIX + Constants.URI_INCOME)
    public ResponseEntity<Void> addIncome(@Valid @RequestBody ProductIncomeForm productIncomeForm,
                                          UriComponentsBuilder ucBuilder) {
        try {
            log.debug("Trying to save new income: {}" + productIncomeForm.toString());
            Integer id = productIncomeService.saveProductIncome(productIncomeForm);
            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(ucBuilder.path("/income/{id}").buildAndExpand(id).toUri());
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            log.error("Can not save new income: " + ex.getMessage());
            throw new CannotSaveException("Can not save new income", ex);
        }
    }
}
