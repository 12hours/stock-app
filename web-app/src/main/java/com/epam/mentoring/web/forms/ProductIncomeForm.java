package com.epam.mentoring.web.forms;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ProductIncomeForm {
    private Long orderNumber;
    private Date date;
    private Integer quantity;
    private Integer productId;
    private Integer supplierId;
    private Integer userId;
}
