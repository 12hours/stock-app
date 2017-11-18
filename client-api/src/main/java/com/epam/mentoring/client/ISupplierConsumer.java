package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Supplier;

import java.util.List;

public interface ISupplierConsumer {

    Integer saveSupplier(Supplier supplier) throws ServerDataAccessException;

    Supplier findSupplier(Integer id) throws ServerDataAccessException;

    List<Supplier> findAll() throws ServerDataAccessException;
}
