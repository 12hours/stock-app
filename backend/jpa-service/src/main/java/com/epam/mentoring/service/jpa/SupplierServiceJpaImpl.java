package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.service.SupplierService;
import com.epam.mentoring.service.jpa.dao.AbstractDao;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SupplierServiceJpaImpl extends AbstractDao<Supplier> implements SupplierService {

    public SupplierServiceJpaImpl(EntityManagerFactory emf) {
        super(Supplier.class, emf);
    }

    @Override
    public List<Supplier> getAllSuppliers() throws DataAccessException {
        try {
            return findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get list of suppliers"){};
        }
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
        try {
            update(supplier.getId(), supplier);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not update supplier"){};
        }
    }

    @Override
    public void deleteSupplier(Integer id) throws DataAccessException {
        try {
            remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not remove supplier"){};
        }
    }

    @Override
    public Supplier getSupplierById(Integer id) throws DataAccessException {
        try {
            return find(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Can not get supplier"){};
        }
    }
}
