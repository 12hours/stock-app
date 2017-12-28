package com.epam.mentoring.service;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface for Supplier service
 */
public interface SupplierService {
    List<Supplier> getAllSuppliers() throws DataAccessException;

    Integer saveSupplier(Supplier supplier) throws DataAccessException;

    Integer saveSupplier(SupplierForm supplierForm);

    void updateSupplier(Supplier supplier) throws DataAccessException;

    int deleteSupplier(Integer id) throws DataAccessException;

    Supplier getSupplierById(Integer id) throws DataAccessException;
}
