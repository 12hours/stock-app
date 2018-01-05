package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeView {
    private Integer id;
    private String name;
    private HashMap links;
}
