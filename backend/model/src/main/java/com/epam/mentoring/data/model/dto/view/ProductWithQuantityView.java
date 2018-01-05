package com.epam.mentoring.data.model.dto.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ProductWithQuantityView {
    private ProductView product;
    private Integer quantity;

    public ProductWithQuantityView(ProductView productView, Integer quantity) {
        this.product = productView;
        this.quantity = quantity;
    }
}
