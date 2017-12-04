package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.rest.config.Constants;
import com.epam.mentoring.rest.error.CannotSaveException;
import com.epam.mentoring.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(value = Constants.URI_API_PREFIX + Constants.URI_SUPPLIER)
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        log.debug("Getting supplier list");
        try {
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<List<Supplier>> responseEntity =
                    new ResponseEntity<List<Supplier>>(suppliers, httpHeaders, HttpStatus.OK);
            return responseEntity;
        } catch (DataAccessException ex) {
            log.error("Can not get suppliers list: {}", ex);
            throw ex;
        }
    }

    @PostMapping(value = Constants.URI_API_PREFIX + Constants.URI_SUPPLIER)
    public ResponseEntity<Void> addSupplier(@Valid @RequestBody SupplierForm supplierForm) {
        log.debug("Trying to save new supplier");
        try {
            Integer id = supplierService.saveSupplier(supplierForm);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            log.error("Can not add new supplier: {}", ex);
            throw new CannotSaveException("Can not add new supplier", ex);
        }
    }

}
