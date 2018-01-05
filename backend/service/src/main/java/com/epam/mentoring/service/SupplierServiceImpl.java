package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.SupplierDao;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class.getName());

    SupplierDao supplierDao;

    public SupplierServiceImpl(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws DataAccessException {
        List<Supplier> suppliers = supplierDao.getAllSuppliers();
        logger.debug("Getting all Suppliers. Found " + suppliers.size() + " items");
        return suppliers;
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws DataAccessException {
        Assert.notNull(supplier, "No supplier provided for saving");
        Assert.notNull(supplier.getName(), "No name for supplier provided");
        logger.debug("Saving Supplier " + supplier.toString());
        return supplierDao.addSupplier(supplier);
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) {
        Assert.notNull(supplierForm, "No supplier form provided");
        Supplier supplier = DTOUtils.map(supplierForm, Supplier.class);
        return saveSupplier(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) throws DataAccessException {
        Assert.notNull(supplier, "No supplier provided for updating");
        Assert.notNull(supplier.getId(), "No supplier id for updating");
        Assert.notNull(supplier.getName(), "No name for supplier updating");
        logger.debug("Updating Supplier with id: {}", supplier.getId());
        supplierDao.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id provided");
        logger.debug("Deleting item with id: {}", id);
        supplierDao.deleteSupplier(id);
        return 0;
    }

    @Override
    public Supplier getSupplierById(Integer id) throws DataAccessException {
        Assert.notNull(id, "No id provided for Supplier");
        logger.debug("Getting Supplier with id " + id);
        try {
            Supplier supplier = supplierDao.getSupplierById(id);
            return supplier;
        } catch (EmptyResultDataAccessException e) {
            logger.debug("No object found");
            return null;
        }
    }
}
