package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIncomeView {
    private Integer id;
    private Integer quantity;
    private Long orderNumber;
    private LocalDate date;
    private Integer productId;
    private Integer userId;
    private Integer supplierId;
    private HashMap links;
}
