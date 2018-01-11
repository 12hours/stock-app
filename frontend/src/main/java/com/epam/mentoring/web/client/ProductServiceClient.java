package com.epam.mentoring.web.client;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;

public interface ProductServiceClient {

    ProductView findProductById(Integer id);

    CollectionDTO<ProductView> findAllProducts();

    CollectionDTO<ProductWithQuantityView> findAllProductsWithQuantities();

    Integer saveProduct(ProductForm productForm);

}
