package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.service.ProductTypeService;
import com.epam.mentoring.service.SupplierService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class SupplierServiceJpaImplTest {

    private EntityManagerFactory emf;

    private SupplierService service;

    @Before
    public void before() {
        emf = Persistence.createEntityManagerFactory("PU");
        service = new SupplierServiceJpaImpl(emf);
    }

    @After
    public void after() {
        emf.close();
    }

    @Test
    public void findAllSuppliersTets() {
        List<Supplier> allSuppliers = service.getAllSuppliers();

        assertNotNull(allSuppliers);
        assertEquals(3, allSuppliers.size());
    }

    @Test
    public void findSupplierTest() {
        Supplier supplierById = service.getSupplierById(1);
        assertEquals("Nova Computers", supplierById.getName());
        assertEquals("", supplierById.getDetails());
        assertEquals(Integer.valueOf(1), supplierById.getId());
    }

    @Test
    public void findNotExistingSupplier() {
        Supplier supplierById = service.getSupplierById(99);
        assertNull(supplierById);
    }

    @Test
    public void saveSupplierTest() {
        Supplier supplier = new Supplier(null, "TestSupplier", "TestDetails");
        Integer id = service.saveSupplier(supplier);

        Supplier supplierById = service.getSupplierById(id);
        assertEquals(id, supplierById.getId());
        assertEquals("TestSupplier", supplierById.getName());
        assertEquals("TestDetails", supplierById.getDetails());
    }

    @Test
    public void updateSupplierTest() {
        Supplier supplier = service.getSupplierById(2);
        supplier.setName("NewName");
        supplier.setDetails("NewDetails");
        service.updateSupplier(supplier);

        Supplier supplierById = service.getSupplierById(2);
        assertEquals("NewName", supplierById.getName());
        assertEquals("NewDetails", supplierById.getDetails());
    }

    @Test
    public void deleteSupplierTest() {
        assertNotNull(service.getSupplierById(2));
        service.deleteSupplier(2);
        assertNull(service.getSupplierById(2));
    }
}