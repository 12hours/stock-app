package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ISupplierDao;
import com.epam.mentoring.data.model.Supplier;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

public class SupplierService implements ISupplierService {

    ISupplierDao supplierDao;

    public SupplierService(ISupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws DataAccessException {
        return supplierDao.getAllSuppliers();
    }

    @Override
    public int saveSupplier(Supplier supplier) throws DataAccessException {
        Assert.notNull(supplier, "No supplier provided for saving");
        return supplierDao.addSupplier(supplier);
    }

    @Override
    public int updateSupplier(Supplier supplier) throws DataAccessException {
        return 0;
    }

    @Override
    public int deleteSupplier(Integer id) throws DataAccessException {
        return 0;
    }

    @Override
    public Supplier getSupplierById(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id provided for Supplier");
        return supplierDao.getSupplierById(id);
    }
}
