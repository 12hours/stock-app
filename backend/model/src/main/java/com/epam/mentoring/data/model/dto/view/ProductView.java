package com.epam.mentoring.data.model.dto.view;

import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductView {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer productTypeId;
    private HashMap<String, Object> links;
}
