package com.epam.mentoring.client.direct;

import com.epam.mentoring.client.SupplierConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.service.ISupplierService;
import org.springframework.util.Assert;

import java.util.List;

public class DirectSupplierConsumer implements SupplierConsumer {

    private ISupplierService supplierService;

    public DirectSupplierConsumer(ISupplierService service) {
        this.supplierService = service;
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws ServerDataAccessException {
        Assert.notNull(supplier, "No Supplier object provided");
        return supplierService.saveSupplier(supplier);
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Supplier findSupplier(Integer id) throws ServerDataAccessException {
        Assert.notNull(id, "No id provided for search");
        return supplierService.getSupplierById(id);
    }

    @Override
    public List<Supplier> findAll() throws ServerDataAccessException {
        return supplierService.getAllSuppliers();
    }
}
