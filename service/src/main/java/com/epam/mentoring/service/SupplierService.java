package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.ISupplierDao;
import com.epam.mentoring.data.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

public class SupplierService implements ISupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class.getName());

    ISupplierDao supplierDao;

    public SupplierService(ISupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws DataAccessException {
        List<Supplier> suppliers = supplierDao.getAllSuppliers();
        logger.debug("Getting all Suppliers. Found " + suppliers.size() + " items");
        return suppliers;
    }

    @Override
    public int saveSupplier(Supplier supplier) throws DataAccessException {
        Assert.notNull(supplier, "No supplier provided for saving");
        logger.debug("Saving Supplier " + supplier.toString());
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
        logger.debug("Getting Supplier with id " + id);
        return supplierDao.getSupplierById(id);
    }
}
