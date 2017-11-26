package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.SupplierConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;

import java.util.List;

public class RestSupplierConsumer implements SupplierConsumer {
    @Override
    public Integer saveSupplier(Supplier supplier) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Supplier findSupplier(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<Supplier> findAll() throws ServerDataAccessException {
        return null;
    }
}
