package com.epam.mentoring.service.dummy;

import com.epam.mentoring.data.model.*;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.Date;

public class DummyProductIncomeService implements ProductIncomeService {
    @Override
    public ProductIncome getProductIncomeById(Integer id) throws DataAccessException {
        return new ProductIncome(1, 10, 10010L,
                new Date(System.currentTimeMillis()),
                new Product(1, "Core i3", new BigDecimal(100.0), new ProductType(1, "CPU")),
                new User(1, "Alice", "12345", false),
                new Supplier(0, "Computer univers", "Jasper, TX"));
    }

    @Override
    public Integer saveProductIncome(ProductIncome productIncome) throws DataAccessException {
        return null;
    }

    @Override
    public Integer saveProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {
        return 14;
    }

    @Override
    public void deleteProductIncome(Integer id) throws DataAccessException {

    }

    @Override
    public void updateProductIncome(ProductIncomeForm productIncomeForm) throws DataAccessException {

    }
}
