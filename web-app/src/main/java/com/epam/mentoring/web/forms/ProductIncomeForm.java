package com.epam.mentoring.web.forms;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductIncomeForm {
    private Long orderNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private Integer quantity;
    private Integer productId;
    private Integer supplierId;
    private Integer userId;
}

