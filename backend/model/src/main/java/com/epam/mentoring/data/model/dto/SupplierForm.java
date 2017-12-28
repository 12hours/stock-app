package com.epam.mentoring.data.model.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SupplierForm {
    @NotNull
    private String name;
    @NotNull
    private String details;
}
