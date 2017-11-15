package com.epam.mentoring.data.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.ProductType;

public interface IProductTypeDao {
	ProductType getProductTypeById(Integer id) throws DataAccessException;
	List<ProductType> getAllProductTypes() throws DataAccessException;
	int updateProductType(ProductType productType) throws DataAccessException;
	int addProductType(ProductType productType) throws DataAccessException;
	int deleteProductType(Integer id) throws DataAccessException;
}
