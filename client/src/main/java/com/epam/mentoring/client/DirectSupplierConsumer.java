package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.dao.ISupplierDao;
import com.epam.mentoring.data.model.Supplier;
import org.springframework.util.Assert;

import java.util.List;

public class DirectSupplierConsumer implements ISupplierConsumer {

    private ISupplierDao dao;

    public DirectSupplierConsumer(ISupplierDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws ServerDataAccessException {
        Assert.notNull(supplier, "No Supplier object provided");
        return dao.addSupplier(supplier);
    }

    @Override
    public Supplier findSupplier(Integer id) throws ServerDataAccessException {
        Assert.notNull(id, "No id provided for search");
        return dao.getSupplierById(id);
    }

    @Override
    public List<Supplier> findAll() throws ServerDataAccessException {
        return dao.getAllSuppliers();
    }
}
