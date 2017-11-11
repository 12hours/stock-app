package com.epam.mentoring.data.dao;

import com.epam.mentoring.data.model.ProductIncome;

public interface IProductIncomeDao {
	ProductIncome getProductIncomeById(int id);
	void addProductIncome(ProductIncome productIncome);
}
