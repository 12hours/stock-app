package com.epam.mentoring.data.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductWithQuantityView {
    Integer id;
    String productName;
    Integer quantity;
}
