package com.epam.mentoring.data.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductIncomeForm {
    @NotNull
    private Long orderNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private Integer quantity;
    @NotNull
    private Integer productId;
    private Integer supplierId;
    private Integer userId;
}

