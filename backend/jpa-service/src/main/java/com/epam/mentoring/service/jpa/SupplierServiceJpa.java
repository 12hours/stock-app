package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.service.SupplierService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class SupplierServiceJpa extends AbstractDao<Supplier> implements SupplierService {

    public SupplierServiceJpa() {
        super(Supplier.class);
    }

    @Override
    public List<Supplier> getAllSuppliers() throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) {
        return null;
    }

    @Override
    public void updateSupplier(Supplier supplier) throws DataAccessException {

    }

    @Override
    public int deleteSupplier(Integer id) throws DataAccessException {
        return 0;
    }

    @Override
    public Supplier getSupplierById(Integer id) throws DataAccessException {
        return null;
    }
}
