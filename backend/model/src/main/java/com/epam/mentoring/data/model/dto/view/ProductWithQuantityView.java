package com.epam.mentoring.data.model.dto.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ProductWithQuantityView {
    private Integer id;
    private String productName;
    private Integer quantity;

    public ProductWithQuantityView(Integer id, String productName, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }
}
