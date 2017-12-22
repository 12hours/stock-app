package com.epam.mentoring.service.dummy;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.service.SupplierService;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class DummySupplierService implements SupplierService {
    @Override
    public List<Supplier> getAllSuppliers() throws DataAccessException {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(0, "Computer univers", "Jasper, TX"));
        suppliers.add(new Supplier(1, "Computer planet", "LA, CA"));
        return suppliers;
    }

    @Override
    public Integer saveSupplier(Supplier supplier) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveSupplier(SupplierForm supplierForm) {
        return 15;
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
        return new Supplier(0, "Computer univers", "Jasper, TX");
    }
}
