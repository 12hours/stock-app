package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductView {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer productTypeId;
    private Map<String, Object> links;
}
