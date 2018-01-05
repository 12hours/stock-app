package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIncomeView {
    private Integer id;
    private Integer quantity;
    private Long orderNumber;
    private LocalDate date;
    private ProductView product;
    private UserView user;
    private SupplierView supplier;
}
