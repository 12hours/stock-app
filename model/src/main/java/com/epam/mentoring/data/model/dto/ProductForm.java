package com.epam.mentoring.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
    private String name;
    private BigDecimal price;
    private Integer productTypeId;
}
