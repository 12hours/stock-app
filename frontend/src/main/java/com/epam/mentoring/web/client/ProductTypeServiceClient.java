package com.epam.mentoring.web.client;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;

public interface ProductTypeServiceClient
{
    CollectionDTO<ProductTypeView> findAllProductTypes();

    Integer saveProductType(ProductTypeForm productTypeForm);
}
