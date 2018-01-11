package com.epam.mentoring.web.client;

import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;

public interface ProductIncomeServiceClient {

    Integer saveProductIncome(ProductIncomeForm productIncomeForm);

}
