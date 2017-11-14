package com.epam.mentoring.data.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;

import com.epam.mentoring.data.model.ProductType;

public class ProductTypeDaoImpl implements IProductTypeDao {

	public ProductTypeDaoImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ProductType getProductTypeById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductType> getAllProductTypes() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProductType(ProductType productType) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addProductType(ProductType productType) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProductType(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
