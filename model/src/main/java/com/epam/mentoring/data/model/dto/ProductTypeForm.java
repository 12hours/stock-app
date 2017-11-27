package com.epam.mentoring.data.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductTypeForm {
    @NotNull
    private String name;
}
