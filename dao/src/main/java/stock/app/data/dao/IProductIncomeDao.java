package stock.app.data.dao;

import stock.app.data.model.ProductIncome;

public interface IProductIncomeDao {
	ProductIncome getProductIncomeById(int id);
	void addProductIncome(ProductIncome productIncome);
}
