package com.epam.mentoring.service;

import com.epam.mentoring.data.dao.SupplierDao;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.test.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SupplierServiceImplTest {

    @Mock
    SupplierDao dao;

    @Captor
    ArgumentCaptor<Supplier> supplierArgumentCaptor;

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;

    SupplierService supplierService;

    @Before
    public void setup() {
        when(dao.getAllSuppliers()).thenReturn(TestData.suppliers());
        when(dao.addSupplier(any(Supplier.class))).thenReturn(10);

        supplierService = new SupplierServiceImpl(dao);
    }

    @Test
    public void getAllSuppliersTest() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        assertNotNull(suppliers);
        assertEquals(TestData.suppliers(), suppliers);
        verify(dao, times(1)).getAllSuppliers();
    }

    @Test
    public void saveSupplierTest() {
        Supplier supplier = TestData.suppliers().get(0);
        Integer id = supplierService.saveSupplier(supplier);
        assertEquals(Integer.valueOf(10), id);
        verify(dao, times(1)).addSupplier(any(Supplier.class));
        verify(dao).addSupplier(supplierArgumentCaptor.capture());
        assertEquals(supplier, supplierArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveSupplierNullNameTest() {
        Supplier supplier = TestData.suppliers().get(0);
        supplier.setId(null);
        supplier.setName(null);
        supplierService.saveSupplier(supplier);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveSupplierNullTest() {
        Supplier supplier = null;
        supplierService.saveSupplier(supplier);
    }

    @Test
    public void saveSupplierFormTest() {
        SupplierForm supplierForm = new SupplierForm("testName", "testDetails");
        Integer id = supplierService.saveSupplier(supplierForm);
        assertEquals(Integer.valueOf(10), id);
        verify(dao, times(1)).addSupplier(any(Supplier.class));
        verify(dao).addSupplier(supplierArgumentCaptor.capture());
        assertEquals(new Supplier(null, "testName", "testDetails"), supplierArgumentCaptor.getValue());
    }

    @Test
    public void saveSupplierFormNullDetailsTest() {
        SupplierForm supplierForm = new SupplierForm("testName", null);
        Integer id = supplierService.saveSupplier(supplierForm);
        assertEquals(Integer.valueOf(10), id);
        verify(dao, times(1)).addSupplier(any(Supplier.class));
        verify(dao).addSupplier(supplierArgumentCaptor.capture());
        assertEquals(new Supplier(null, "testName", null), supplierArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveSupplierFormNullNameTest() {
        SupplierForm supplierForm = new SupplierForm(null, "testDetails");
        Integer id = supplierService.saveSupplier(supplierForm);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveSupplierFormNullTest() {
        SupplierForm supplierForm = null;
        Integer id = supplierService.saveSupplier(supplierForm);
    }

    @Test
    public void deleteSupplierTets() {
        supplierService.deleteSupplier(3);
        verify(dao, times(1)).deleteSupplier(anyInt());
        verify(dao).deleteSupplier(integerArgumentCaptor.capture());
        assertEquals(3, integerArgumentCaptor.getValue().intValue());
    }

    @Test
    public void updateSupplierTest() {
        Supplier supplier = TestData.suppliers().get(0);
        supplierService.updateSupplier(supplier);
        verify(dao, times(1)).updateSupplier(any(Supplier.class));
        verify(dao).updateSupplier(supplierArgumentCaptor.capture());
        assertEquals(supplier, supplierArgumentCaptor.getValue());
    }
}