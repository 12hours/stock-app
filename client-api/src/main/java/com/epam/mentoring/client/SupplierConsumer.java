package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;

import java.util.List;

public interface SupplierConsumer {

    Integer saveSupplier(Supplier supplier) throws ServerDataAccessException;

    Integer saveSupplier(SupplierForm supplierForm) throws ServerDataAccessException;

    Supplier findSupplier(Integer id) throws ServerDataAccessException;

    List<Supplier> findAll() throws ServerDataAccessException;
}
