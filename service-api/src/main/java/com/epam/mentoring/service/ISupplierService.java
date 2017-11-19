package com.epam.mentoring.service;

import com.epam.mentoring.data.model.Supplier;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface for Supplier service
 */
public interface ISupplierService {
    List<Supplier> getAllSuppliers() throws DataAccessException;
    int saveSupplier(Supplier supplier) throws DataAccessException;
    int updateSupplier(Supplier supplier) throws DataAccessException;
    int deleteSupplier(Integer id) throws DataAccessException;
    Supplier getSupplierById(Integer id) throws DataAccessException;
}
