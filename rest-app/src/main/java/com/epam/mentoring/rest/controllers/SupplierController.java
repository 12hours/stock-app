package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.ISupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SupplierController {

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class.getName());

    private ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        logger.debug("GET /supplier: getting supplier list");
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<Supplier>> responseEntity =
                new ResponseEntity<List<Supplier>>(suppliers, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/supplier")
    public ResponseEntity<Void> addSupplier(@Valid @RequestBody SupplierForm supplierForm) {
        logger.debug("POST /supplier");
        try {
            Integer id = supplierService.saveSupplier(supplierForm);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            logger.debug("can not add new product type: " + ex.getMessage());
            throw new CannotSaveException();
        }
    }

}
