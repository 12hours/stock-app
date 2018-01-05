package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithQuantityView {
    private Integer id;
    private String productName;
    private Integer quantity;
    private HashMap links;
}
