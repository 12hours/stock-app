package com.epam.mentoring.service;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

/**
 * Interface for Supplier service
 */
public interface SupplierService {
    List<Supplier> getAllSuppliers() throws DataAccessException;

    Integer saveSupplier(Supplier supplier) throws DataAccessException;

    Integer saveSupplier(SupplierForm supplierForm);

    void updateSupplier(Supplier supplier) throws DataAccessException;

    void deleteSupplier(Integer id) throws DataAccessException;

    Supplier getSupplierById(Integer id) throws DataAccessException;

    Collection<ProductIncome> getAllIncomesOfSupplier(Integer id);

    Supplier getSupplierForProductIncome(Integer productIncomeId);
}
