package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.DTOUtils;
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
        return findAll();
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws DataAccessException {
        try {
            persist(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not save supplier"){};
        }
        return supplier.getId();
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) {
        Supplier supplier = DTOUtils.map(supplierForm, Supplier.class);
        return saveSupplier(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) throws DataAccessException {
        update(supplier.getId(), supplier);
    }

    @Override
    public int deleteSupplier(Integer id) throws DataAccessException {
        remove(id);
        return 0;
    }

    @Override
    public Supplier getSupplierById(Integer id) throws DataAccessException {
        return find(id);
    }
}
