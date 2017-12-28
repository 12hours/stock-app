package com.epam.mentoring.data.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductView {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer productTypeId;
}
