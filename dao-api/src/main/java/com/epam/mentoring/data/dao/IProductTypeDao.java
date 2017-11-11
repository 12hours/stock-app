package com.epam.mentoring.data.dao;

import java.util.List;

import com.epam.mentoring.data.model.ProductType;

public interface IProductTypeDao {
	ProductType getProductTypeById(int id);
	List<ProductType> getAllProductTypes();
	int updateProductType(ProductType productType);
	int addProductType(ProductType productType);
	int deleteProductType(int id);
}
