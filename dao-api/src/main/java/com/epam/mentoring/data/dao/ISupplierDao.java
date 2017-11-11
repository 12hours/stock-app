package com.epam.mentoring.data.dao;

import java.util.List;

import com.epam.mentoring.data.model.Supplier;

public interface ISupplierDao {
	Supplier getSupplierById();
	List<Supplier> getAllSuppliers();
	int addSupplier(Supplier supplier);
	int updateSupplier(Supplier supplier);
	int deleteSupplier(int id);
}
